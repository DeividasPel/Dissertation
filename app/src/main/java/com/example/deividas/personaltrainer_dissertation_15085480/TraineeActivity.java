package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TraineeActivity extends AppCompatActivity implements SensorEventListener {
    TextView usernameDashboard, steps, calories, target;
    DatabaseHelper db;
    private String username;
    RelativeLayout buttonMealPlan, buttonUpdateRecords, buttonMessages, buttonSettings, buttonSchedule, buttonActivity;
    SensorManager sensorManager;
    boolean running = false;
    double weight, height, stepsCount, caloriesBurnedPerMile, strip, stepCountMile, conversationFactor, caloriesBurned;
    final double walkingFactor = 0.57;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee);

        //Steps, calories, target
        steps = findViewById(R.id.tv_steps);
        calories = findViewById(R.id.tv_calories);
        target = findViewById(R.id.tv_target);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //END Steps, calories, target


        //Displaying name on dashboard from DB
        usernameDashboard = (TextView) findViewById(R.id.username_dashboard);
        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");
        Cursor cursor = db.retrieveData(username);
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA ABOUT THIS USER", Toast.LENGTH_SHORT).show();
            }
        else{
            while(cursor.moveToNext()){
                usernameDashboard.setText(cursor.getString(1) + " " + cursor.getString(2));
                //calories.setText(cursor.getString(14));
                target.setText(cursor.getString(15));
                weight = Double.parseDouble(cursor.getString(6));
                height = Double.parseDouble(cursor.getString(4));
                stepsCount = Double.parseDouble(cursor.getString(13));
                }
            }
        //End of displaying name from DB


        //Open Meal Plan activity
        buttonMealPlan = findViewById(R.id.btn_plan);
        buttonMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMealPlan();
            }
        });
        //End Meal Plan activity

        //Open update records activity
        buttonUpdateRecords = findViewById(R.id.btn_update);
        buttonUpdateRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecords();
            }
        });
        //End open records activity

        //Open messages activity
        buttonMessages = findViewById(R.id.btn_messages);
        buttonMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessages();
            }
        });
        //End messages activity

        //Open settings activity
        buttonSettings = findViewById(R.id.btn_settings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
        //End settings activity

        //Open workouts activity
        buttonSchedule = findViewById(R.id.btn_schedule);
        buttonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkouts();
            }
        });
        //End workouts activity

        //Open myactivity activity
        buttonActivity = findViewById(R.id.btn_activity);
        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        //End myactivity activity

    }

    public void openMealPlan(){
        Intent intent = new Intent(this, MealPlan.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void openRecords(){
        Intent intent = new Intent(this, UpdateRecords.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void openMessages(){
        Intent intent = new Intent(this, Messages.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void openSettings(){
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void openWorkouts(){
        Intent intent = new Intent(this, Workout.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void openActivity(){
        Intent intent = new Intent(this, Myactivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else{
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running == true){
            steps.setText(String.valueOf((int)event.values[0]));
            db.updateSteps(String.valueOf(event.values[0]), username);

            //Count calories
            Cursor cursor = db.retrieveData(username);
            while (cursor.moveToNext()) {
                stepsCount = Double.parseDouble(cursor.getString(13));
            }
            caloriesBurnedPerMile = walkingFactor * (weight * 2.2);
            strip = height * 0.415;
            stepCountMile = 160934.4 / strip;
            conversationFactor = caloriesBurnedPerMile / stepCountMile;
            caloriesBurned = stepsCount * conversationFactor;
            calories.setText(String.valueOf((int)caloriesBurned));
            db.updateCalories(String.valueOf(caloriesBurned), username);
            //End count calories
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

