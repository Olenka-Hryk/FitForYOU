package com.example.olenka.fitforyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.olenka.fitforyou.Custom.WorkoutDoneDecorator;
import com.example.olenka.fitforyou.DataBase.FitForYouDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Calendar extends AppCompatActivity {
    MaterialCalendarView materialCalendarView;
    HashSet<CalendarDay> list = new HashSet<>();
    FitForYouDB fitDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        fitDB = new FitForYouDB(this);
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar);
        List<String> workoutDay = fitDB.getWorkoutDay();
        HashSet<CalendarDay> convertedList = new HashSet<>();
        for(String value:workoutDay)
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));
    }
}
