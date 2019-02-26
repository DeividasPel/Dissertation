package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TrainerActivity extends AppCompatActivity {
    private TextView usernameDashboard, traineeDashboard;
    DatabaseHelper db;
    String trainerName, traineeName, traineeEmail;
    RelativeLayout buttonMessages, buttonActivity, btnMealPlans, btnRecords, btnWorkout, btnProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        traineeEmail = getIntent().getStringExtra("traineeEmail");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        buttonMessages = findViewById(R.id.btn_messages_trainer);
        buttonMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainerMessages();
            }
        });

        buttonActivity = findViewById(R.id.btn_activity_trainer);
        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainerActivity();
            }
        });

        btnMealPlans = findViewById(R.id.btn_plan_trainer);
        btnMealPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainerMealPlan();
            }
        });

        btnRecords = findViewById(R.id.btn_update_trainer);
        btnRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainerRecords();
            }
        });

        btnWorkout = findViewById(R.id.btn_workout_trainer);
        btnWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainerWorkouts();
            }
        });

        btnProgress = findViewById(R.id.btn_progress);
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainerProgress();
            }
        });
    }

    public void openTrainerWorkouts(){
        Intent intent = new Intent(this, TrainerWorkoutSelection.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        startActivity(intent);
    }


    public void openTrainerRecords(){
        Intent intent = new Intent(this, TrainerRecords.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        startActivity(intent);
    }

    public void openTrainerActivity(){
        Intent intent = new Intent(this, MyActivityTrainer.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        startActivity(intent);
    }

    public void openTrainerMealPlan(){
        Intent intent = new Intent(this, TrainerMealPlan.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        startActivity(intent);
    }

    public void openTrainerMessages(){
        Intent intent = new Intent(this, TrainerMessages.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        startActivity(intent);
    }

    public void openTrainerProgress(){
        Intent intent = new Intent(this, TrainerProgress.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        startActivity(intent);
    }
}
