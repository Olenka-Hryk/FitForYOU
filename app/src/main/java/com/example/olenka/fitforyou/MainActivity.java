package com.example.olenka.fitforyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnExercises, btnSettings, btnCalendar, btnReminder;
    ImageView play_daily_training;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExercises = (Button)findViewById(R.id.btnExercise);
        btnSettings = (Button)findViewById(R.id.btnSetting);
        btnReminder = (Button)findViewById(R.id.btnReminder);
        btnCalendar = (Button)findViewById(R.id.btnCalendar);
        play_daily_training = (ImageView)findViewById(R.id.play_daily_training);

        //список вправ
        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListExercises.class);
                startActivity(intent);
            }
        });

        //розрахування калорій
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalculateCalories.class);
                startActivity(intent);
            }
        });

        //встановлення нагадування
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Reminder.class);
                startActivity(intent);
            }
        });

        //календар вправ
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });

        play_daily_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Daily_training.class);
                startActivity(intent);
            }
        });
    }
}
