package com.example.olenka.fitforyou;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.olenka.fitforyou.DataBase.FitForYouDB;
import com.example.olenka.fitforyou.Model.Exercise;
import com.example.olenka.fitforyou.Utils.Common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class Daily_training extends AppCompatActivity {
    Button btnStart;
    ImageView ex_image;
    TextView txtGetReady, txtCountDown, txtTimer, ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;

    int ex_id=0, limit_time=0;
    List<Exercise> list = new ArrayList<>();
    FitForYouDB fitDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);
        initData();

        fitDB = new FitForYouDB(this);


        btnStart = (Button)findViewById(R.id.btnStart);
        ex_image = (ImageView)findViewById(R.id.detail_image);
        ex_name = (TextView)findViewById(R.id.title);
        txtCountDown = (TextView)findViewById(R.id.txtCountDown);
        txtGetReady = (TextView)findViewById(R.id.txtGetReady);
        txtTimer = (TextView)findViewById(R.id.timer);
        layoutGetReady = (LinearLayout)findViewById(R.id.layout_get_ready);
        progressBar = (MaterialProgressBar)findViewById(R.id.progressBar);

        //Set data
        progressBar.setMax(list.size());
        setExerciseInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnStart.getText().toString().toLowerCase().equals("start"))
                {
                    showGetReady();
                    btnStart.setText("done");
                }
                else if(btnStart.getText().toString().toLowerCase().equals("done"))
                {
                    if(fitDB.getReminderMode() == 0)
                        exercisesEasyModeCountDown.cancel();
                    else if(fitDB.getReminderMode() == 1)
                        exercisesMediumModeCountDown.cancel();
                    else if(fitDB.getReminderMode() == 2)
                        exercisesHardModeCountDown.cancel();

                    if(ex_id < list.size())
                    {
                        showRestTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        txtTimer.setText("");
                    }
                    else
                        showFinished();
                }
                else
                {
                    if(fitDB.getReminderMode() == 0)
                        exercisesEasyModeCountDown.cancel();
                    else if(fitDB.getReminderMode() == 1)
                        exercisesMediumModeCountDown.cancel();
                    else if(fitDB.getReminderMode() == 2)
                        exercisesHardModeCountDown.cancel();

                    restTimeCountDown.cancel();

                    if(ex_id < list.size())
                        setExerciseInformation(ex_id);
                    else showFinished();
                }
            }
        });
    }

    private void setExerciseInformation(int id) {
        ex_image.setImageResource(list.get(id).getImage_id());
        ex_name.setText(list.get(id).getName());
        btnStart.setText("Start");
        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.INVISIBLE);
    }

    private void showGetReady()
    {
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("GET READY");
        new CountDownTimer(6000, 1000)
        {
            @Override
            public void onTick(long l) {
                txtCountDown.setText(""+(l-1000)/1000);
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showRestTime(){
        ex_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setText("Skip");
        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        restTimeCountDown.start();

        txtGetReady.setText("REST TIME");
    }

    private void showExercises(){
        if(ex_id< list.size())
        {
            ex_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if(fitDB.getReminderMode() == 0)
                exercisesEasyModeCountDown.start();
            else if(fitDB.getReminderMode() == 1)
                exercisesMediumModeCountDown.start();
            else if(fitDB.getReminderMode() == 2)
                exercisesHardModeCountDown.start();

            //Set data
            ex_image.setImageResource(list.get(ex_id).getImage_id());
            ex_name.setText(list.get(ex_id).getName());
        }
        else
            showFinished();
    }

    private void showFinished(){
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);
        txtGetReady.setText("FINISHED!!!");
        txtCountDown.setText("Congratulation! \nYou're done with today exercises");
        txtCountDown.setTextSize(20);

        //Save workout one to DB
        fitDB.saveDay(""+ Calendar.getInstance().getTimeInMillis());
    }


    CountDownTimer exercisesEasyModeCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()-1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");
                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else
                showFinished();
        }
    };
    CountDownTimer exercisesMediumModeCountDown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()-1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");
                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else
                showFinished();
        }
    };
    CountDownTimer exercisesHardModeCountDown = new CountDownTimer(Common.TIME_LIMIT_HARD, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()-1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");
                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else
                showFinished();
        }
    };

    CountDownTimer restTimeCountDown = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {
            txtCountDown.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            setExerciseInformation(ex_id);
            showExercises();
        }
    };

    int variant =1;
    private void initData() {
        if(variant == 1) {
            list.add(new Exercise(R.drawable.yoga1, "Pose Camel"));
            list.add(new Exercise(R.drawable.yoga2, "Pose Scorpio"));
            list.add(new Exercise(R.drawable.yoga3, "Pose Lotus"));
            list.add(new Exercise(R.drawable.yoga4, "Pose Easy"));
            list.add(new Exercise(R.drawable.yoga5, "Pose Marichiasan"));
            list.add(new Exercise(R.drawable.yoga6, "Pose Sukhasana"));
            list.add(new Exercise(R.drawable.yoga7, "Pose Dove"));
            list.add(new Exercise(R.drawable.yoga8, "Pose King fish"));
            list.add(new Exercise(R.drawable.yoga9, "Pose Forearm"));
            list.add(new Exercise(R.drawable.yoga10, "Pose Triangle"));
            list.add(new Exercise(R.drawable.yoga11, "Pose Cow"));
            list.add(new Exercise(R.drawable.yoga12, "Pose Virabhadrasin І"));
            list.add(new Exercise(R.drawable.yoga13, "Pose Ashva Sanchalanas"));
            list.add(new Exercise(R.drawable.yoga14, "Pose Ashiahana"));
            list.add(new Exercise(R.drawable.yoga15, "Pose Simhisana"));
            list.add(new Exercise(R.drawable.yoga16, "Pose Mountains"));
            list.add(new Exercise(R.drawable.exercise27, "Pose Adho Svasana"));
            variant=2;
        }
        else if (variant== 2) {
            list.add(new Exercise(R.drawable.exercise16, "The slopes"));
            list.add(new Exercise(R.drawable.exercise17, "Strap"));
            list.add(new Exercise(R.drawable.exercise18, "Fitness ball І"));
            list.add(new Exercise(R.drawable.exercise28, "Fitness ball ІІ"));
            list.add(new Exercise(R.drawable.exercise19, "Press I"));
            list.add(new Exercise(R.drawable.exercise29, "Press II"));
            list.add(new Exercise(R.drawable.exercise20, "Elevation legs"));
            list.add(new Exercise(R.drawable.exercise21, "Legs"));
            list.add(new Exercise(R.drawable.exercise22, "Stretching"));
            list.add(new Exercise(R.drawable.exercise23, "Squat I"));
            list.add(new Exercise(R.drawable.exercise24, "Side plank"));
            list.add(new Exercise(R.drawable.exercise25, "Squat II"));
            list.add(new Exercise(R.drawable.exercise26, "Mega complex"));
            variant=1;
        }
    }
}
