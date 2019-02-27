package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrainerList extends AppCompatActivity {
    private ListView traineeList;
    private TextView usernameDashboard, emptyList;
    DatabaseHelper db;
    String trainerName, traineeName, traineeEmail;
    Button checkProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);

        db = new DatabaseHelper(this);

        trainerName = getIntent().getStringExtra("trainer");

        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        emptyList = findViewById(R.id.tv_empty);
        usernameDashboard.setText(trainerName);


        traineeList = findViewById(R.id.lv_trainees);
        ArrayList<String> arrayList = db.getTraineeList(trainerName);
        final ArrayList<String> arrayList1 = db.getTraineeListEmail(trainerName);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        traineeList.setAdapter(arrayAdapter);
        if (traineeList.getCount() == 0){
            traineeList.setEmptyView(emptyList);
            emptyList.setText("LIST IS EMPTY");
        }
        else{
            traineeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    traineeName = ((TextView)view).getText().toString();
                    traineeEmail = arrayList1.get(position);
                    Toast.makeText(getBaseContext(), traineeName, Toast.LENGTH_LONG).show();
                    openTrainerActivity();
                }
            });
        }

        checkProgress = findViewById(R.id.btn_check_progress);
        checkProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProgressActivity();
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

    public void openProgressActivity(){
        Intent intent = new Intent(this, AllTraineeProgres.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        intent.putExtra("traineeEmail", traineeEmail);
        startActivity(intent);
    }

}
