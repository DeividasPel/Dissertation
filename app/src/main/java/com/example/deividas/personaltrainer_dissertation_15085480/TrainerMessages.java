package com.example.deividas.personaltrainer_dissertation_15085480;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TrainerMessages extends AppCompatActivity {
    private TextView usernameDashboard, traineeDashboard;
    String trainerName, traineeName;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_messages);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);
    }
}
