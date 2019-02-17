package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrainerList extends AppCompatActivity {
    private ListView traineeList;
    private TextView usernameDashboard, emptyList;
    DatabaseHelper db;
    String trainerName, traineeName;

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
                    Toast.makeText(getBaseContext(), traineeName, Toast.LENGTH_LONG).show();
                    openTrainerActivity();
                }
            });
        }



    }

    public void openTrainerActivity(){
        Intent intent = new Intent(this, TrainerActivity.class);
        intent.putExtra("trainer", trainerName);
        intent.putExtra("trainee", traineeName);
        startActivity(intent);
    }

}
