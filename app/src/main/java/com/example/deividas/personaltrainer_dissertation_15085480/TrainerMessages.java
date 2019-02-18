package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TrainerMessages extends AppCompatActivity {
    private TextView usernameDashboard, traineeDashboard;
    private EditText message;
    String trainerName, traineeName, traineeEmail;
    DatabaseHelper db;
    ImageButton btnSend;
    ListView messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_messages);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        traineeEmail = getIntent().getStringExtra("traineeEmail");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        messages = findViewById(R.id.lv_messageS_trainer);
        ArrayList<String> arrayList = db.getMessagesList(traineeEmail);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        messages.setAdapter(arrayAdapter);

        //Sending message
        btnSend = findViewById(R.id.btn_send);
        message = findViewById(R.id.et_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                db.insertNewMessage(traineeEmail, dateFormat.format(date) + " - " + message.getText().toString());
                Toast.makeText(getBaseContext(), "Message succesfully sent!", Toast.LENGTH_SHORT).show();
                openTrainerActivity();
            }
        });
    }

    public void openTrainerActivity(){
        Intent intent = new Intent(this, TrainerActivity.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        startActivity(intent);
    }
}
