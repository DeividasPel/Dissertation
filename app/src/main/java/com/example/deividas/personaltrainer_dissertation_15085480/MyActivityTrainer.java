package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivityTrainer extends AppCompatActivity {
    DatabaseHelper db;
    String trainerName, traineeName, traineeEmail;
    private TextView usernameDashboard, traineeDashboard,  steps, calories, target, stepsToTarget;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trainer);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        traineeEmail = getIntent().getStringExtra("traineeEmail");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        //Display activity information
        steps = findViewById(R.id.tv_steps_trainer);
        calories = findViewById(R.id.tv_calories_trainer);
        target = findViewById(R.id.tv_target_trainer);
        stepsToTarget = findViewById(R.id.tv_steps_left_trainer);
        Cursor cursor = db.retrieveData(traineeEmail);
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA ABOUT THIS USER", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                double stepsInteger = Double.parseDouble(cursor.getString(13));
                double caloriesInteger = Double.parseDouble(cursor.getString(14));
                steps.setText(String.valueOf((int)stepsInteger));
                calories.setText(String.valueOf((int)caloriesInteger));
                target.setText(String.valueOf((int)stepsInteger) + " / " + cursor.getString(15));
                int stepsLeft = Integer.parseInt(cursor.getString(15)) - (int)stepsInteger;
                if (stepsLeft > 0){
                    stepsToTarget.setText("Steps left to target - " + String.valueOf(stepsLeft));
                }
                else{
                    stepsToTarget.setText("YOUR TRAINEE HAS REACHED THEIR GOAL!");
                }
            }
        }
    }
}
