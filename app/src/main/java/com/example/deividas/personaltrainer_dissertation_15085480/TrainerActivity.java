package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TrainerActivity extends AppCompatActivity {
    private TextView usernameDashboard, traineeDashboard;
    DatabaseHelper db;
    String trainerName, traineeName;
    RelativeLayout buttonActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        buttonActivity = findViewById(R.id.btn_activity_trainer);
        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainerMessages();
            }
        });
    }

    public void openTrainerMessages(){
        Intent intent = new Intent(this, TrainerMessages.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        startActivity(intent);
    }
}
