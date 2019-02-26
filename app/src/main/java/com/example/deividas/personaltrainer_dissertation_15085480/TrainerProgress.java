package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TrainerProgress extends AppCompatActivity {
    private TextView usernameDashboard, traineeDashboard, mealProgress, workoutProgress, stepsProgress, stepsProgressTarget;
    String trainerName, traineeName, traineeEmail;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_progress);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        traineeEmail = getIntent().getStringExtra("traineeEmail");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        mealProgress = findViewById(R.id.tv_meal_progress);
        workoutProgress = findViewById(R.id.tv_workout_progress);
        stepsProgress = findViewById(R.id.tv_target);
        stepsProgressTarget = findViewById(R.id.tv_steps_left_progress);
        Cursor cursor = db.retrieveData(traineeEmail);
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA ABOUT THIS USER", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                if(cursor.getString(17).equals("1")){
                    mealProgress.setText("YES");
                }
                else{
                    mealProgress.setText("NO");
                }

                if(cursor.getString(16).equals("1")){
                    workoutProgress.setText("YES");
                }
                else{
                    workoutProgress.setText("NO");
                }

                double stepsInteger = Double.parseDouble(cursor.getString(13));
                double stepsTarget = Double.parseDouble(cursor.getString(15));

                if((int)stepsInteger > (int)stepsTarget){
                    stepsProgress.setText("YES");
                }
                else{
                    stepsProgress.setText("NO");
                }

                int stepsLeft = Integer.parseInt(cursor.getString(15)) - (int)stepsInteger;
                if (stepsLeft > 0){
                    stepsProgressTarget.setText("Steps left to target - " + String.valueOf(stepsLeft));
                }
                else{
                    stepsProgressTarget.setText("STEP GOAL HAS BEEN REACHED!");
                }
            }
        }
    }
}
