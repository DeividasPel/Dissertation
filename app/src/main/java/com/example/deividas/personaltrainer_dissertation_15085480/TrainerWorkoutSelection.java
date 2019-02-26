package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TrainerWorkoutSelection extends AppCompatActivity {
    private TextView usernameDashboard, traineeDashboard;
    DatabaseHelper db;
    String trainerName, traineeName, traineeEmail;
    RelativeLayout viewExisting, newWorkouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_workout_selection);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        traineeEmail = getIntent().getStringExtra("traineeEmail");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        newWorkouts = findViewById(R.id.btn_new_workout);
        newWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainerWorkouts();
            }
        });

        viewExisting = findViewById(R.id.btn_current_workout);
        viewExisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCurrentWorkouts();
            }
        });
    }

    public void openTrainerWorkouts(){
        Intent intent = new Intent(this, TrainerWorkout.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        db.deleteAllWorkouts(traineeEmail);
        startActivity(intent);
    }

    public void openCurrentWorkouts(){
        Intent intent = new Intent(this, TrainerCurrentWorkouts.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        startActivity(intent);
    }
}

