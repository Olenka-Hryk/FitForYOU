package com.example.olenka.fitforyou;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.olenka.fitforyou.DataBase.FitForYouDB;

import java.util.Calendar;
import java.util.Date;

public class Reminder extends AppCompatActivity
{

    Button btnSave;
    RadioButton rdiEasy, rdiMedium, rdiHard;
    ToggleButton swichAlarm;
    TimePicker timePicker;
    RadioGroup rdiGroup;
    FitForYouDB fitDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        //Init view
        rdiGroup = (RadioGroup)findViewById(R.id.rdiGroup);
        rdiEasy = (RadioButton)findViewById(R.id.rdiEasy);
        rdiMedium = (RadioButton)findViewById(R.id.rdiMedium);
        rdiHard = (RadioButton)findViewById(R.id.rdiHard);
        swichAlarm = (ToggleButton)findViewById(R.id.switchAlarm);
        timePicker = (TimePicker)findViewById(R.id.timerPicker);
        btnSave = (Button)findViewById(R.id.btnSave);

        fitDB = new FitForYouDB(this);
        int mode = fitDB.getReminderMode();
        setRadioButton(mode);

        //Event

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWorkoutMode();
                saveAlarm(swichAlarm.isChecked());
                Toast.makeText(Reminder.this, "SAVED!!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setRadioButton(int mode) {
        if(mode ==0 )
            rdiGroup.check(R.id.rdiEasy);
        else if(mode ==1 )
            rdiGroup.check(R.id.rdiMedium);
        else if(mode ==2 )
            rdiGroup.check(R.id.rdiHard);
    }

    private void saveWorkoutMode(){
        int selectedId = rdiGroup.getCheckedRadioButtonId();
        if (selectedId == rdiEasy.getId())
            fitDB.saveReminderMode(0);
        else if (selectedId == rdiMedium.getId())
            fitDB.saveReminderMode(1);
        else if (selectedId == rdiHard.getId())
            fitDB.saveReminderMode(2);
    }

    private void saveAlarm(boolean checked) {
      if(checked)
      {
          AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
          Intent intent;
          PendingIntent pendingIntent;

          intent = new Intent(Reminder.this, AlarmNotification.class);
          pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

          //Set time to alarm
          Calendar calendar = Calendar.getInstance();
          Date toDay = Calendar.getInstance().getTime();
          calendar.set(toDay.getYear(), toDay.getMonth(), toDay.getDay(), timePicker.getHour(), timePicker.getMinute());

          manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
          Log.d("DEBAG", "Alarm will wake at: "+timePicker.getHour()+":"+timePicker.getMinute());
      }
      else
      {
          //Cancel Alarm
          Intent intent = new Intent(Reminder.this, AlarmNotification.class);
          PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
          AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
          manager.cancel(pendingIntent);
      }
    }

}
