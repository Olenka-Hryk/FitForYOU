package com.example.olenka.fitforyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;

import com.example.olenka.fitforyou.Adapter.RecyclerViewAdapter;
import com.example.olenka.fitforyou.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercises extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recycleListView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);
        initData();

        recycleListView =(RecyclerView)findViewById(R.id.list_kind_sport);
        adapter = new RecyclerViewAdapter(exerciseList, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recycleListView.setLayoutManager(layoutManager);
        recycleListView.setAdapter(adapter);
    }

    private void initData() {
        exerciseList.add(new Exercise(R.drawable.yoga1, "Pose Camel"));
        exerciseList.add(new Exercise(R.drawable.yoga2, "Pose Scorpio"));
        exerciseList.add(new Exercise(R.drawable.yoga3, "Pose Lotus"));
        exerciseList.add(new Exercise(R.drawable.yoga4, "Pose Easy"));
        exerciseList.add(new Exercise(R.drawable.yoga5, "Pose Marichiasan"));
        exerciseList.add(new Exercise(R.drawable.yoga6, "Pose Sukhasana"));
        exerciseList.add(new Exercise(R.drawable.yoga7, "Pose Dove"));
        exerciseList.add(new Exercise(R.drawable.yoga8, "Pose King fish"));
        exerciseList.add(new Exercise(R.drawable.yoga9, "Pose Forearm"));
        exerciseList.add(new Exercise(R.drawable.yoga10, "Pose Triangle"));
        exerciseList.add(new Exercise(R.drawable.yoga11, "Pose Cow"));
        exerciseList.add(new Exercise(R.drawable.yoga12, "Pose Virabhadrasin І"));
        exerciseList.add(new Exercise(R.drawable.yoga13, "Pose Ashva Sanchalanas"));
        exerciseList.add(new Exercise(R.drawable.yoga14, "Pose Ashiahana"));
        exerciseList.add(new Exercise(R.drawable.yoga15, "Pose Simhisana"));
        exerciseList.add(new Exercise(R.drawable.yoga16, "Pose Mountains"));
        exerciseList.add(new Exercise(R.drawable.exercise27, "Pose Adho Svasana"));
        exerciseList.add(new Exercise(R.drawable.exercise16, "The slopes"));
        exerciseList.add(new Exercise(R.drawable.exercise17, "Strap"));
        exerciseList.add(new Exercise(R.drawable.exercise18, "Fitness ball І"));
        exerciseList.add(new Exercise(R.drawable.exercise28, "Fitness ball ІІ"));
        exerciseList.add(new Exercise(R.drawable.exercise19, "Press I"));
        exerciseList.add(new Exercise(R.drawable.exercise29, "Press II"));
        exerciseList.add(new Exercise(R.drawable.exercise20, "Elevation legs"));
        exerciseList.add(new Exercise(R.drawable.exercise21, "Legs"));
        exerciseList.add(new Exercise(R.drawable.exercise22, "Stretching"));
        exerciseList.add(new Exercise(R.drawable.exercise23, "Squat I"));
        exerciseList.add(new Exercise(R.drawable.exercise24, "Side plank"));
        exerciseList.add(new Exercise(R.drawable.exercise25, "Squat II"));
        exerciseList.add(new Exercise(R.drawable.exercise26, "Mega complex"));
    }
}
