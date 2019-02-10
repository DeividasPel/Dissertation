package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TraineeActivity extends AppCompatActivity {
    TextView usernameDashboard;
    DatabaseHelper db;
    private String username;
    RelativeLayout buttonMealPlan, buttonUpdateRecords, buttonMessages, buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee);

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

}

