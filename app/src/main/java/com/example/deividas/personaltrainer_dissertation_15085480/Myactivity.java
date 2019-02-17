package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Context;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Myactivity extends AppCompatActivity{


    TextView usernameDashboard, steps, calories, target, stepsToTarget;
    DatabaseHelper db;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivity);

        //Displaying name on dashboard from DB
        usernameDashboard = findViewById(R.id.username_dashboard);
        steps = findViewById(R.id.tv_steps);
        calories = findViewById(R.id.tv_calories);
        target = findViewById(R.id.tv_target);
        stepsToTarget = findViewById(R.id.tv_steps_left);
        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");
        Cursor cursor = db.retrieveData(username);
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA ABOUT THIS USER", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                usernameDashboard.setText(cursor.getString(1) + " " + cursor.getString(2));
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
                    stepsToTarget.setText("YOU HAVE REACHED YOUR GOAL!");
                }
            }
        }
        //End of displaying name from DB


    }
}
