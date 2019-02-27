package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllTraineeProgres extends AppCompatActivity {
    private ListView traineeList;
    private ProgressListAdapter adapter;
    private List<ProgressList> mProgressList;
    private TextView usernameDashboard, emptyList;
    DatabaseHelper db;
    String trainerName, traineeName, traineeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trainee_progres);

        db = new DatabaseHelper(this);

        trainerName = getIntent().getStringExtra("trainer");

        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        emptyList = findViewById(R.id.tv_empty);
        usernameDashboard.setText(trainerName);

        //DB


        ArrayList<String> arrayListTrainerName = db.getTraineeList(trainerName);
        final ArrayList<String> arrayListEmails = db.getTraineeListEmail(trainerName);

        traineeList = findViewById(R.id.lv_progress);
        mProgressList = new ArrayList<>();

        for (int i = 0; arrayListTrainerName.size() > i; i++){
            Cursor cursor = db.retrieveData(arrayListEmails.get(i));
            String meal = null;
            String workout = null;
            String steps = null;
            double stepsInteger = 0;
            double stepsTarget = 0;
            while(cursor.moveToNext()){
                meal = cursor.getString(17);
                workout = cursor.getString(16);

                stepsInteger = Double.parseDouble(cursor.getString(13));
                stepsTarget = Double.parseDouble(cursor.getString(15));
            }
            if (meal.equals("0")){
                meal = "Meals followed - NO";
            }
            else{
                meal = "Meals followed - YES";
            }

            if (workout.equals("0")){
                workout = "Workout routine followed - NO";
            }
            else{
                workout = "Workout routine followed - YES";
            }

            if((int)stepsInteger < (int)stepsTarget){
                steps = "Steps target reached - NO";
            }
            else{
                steps = "Steps target reached - YES";
            }
            mProgressList.add(new ProgressList(i, arrayListTrainerName.get(i), meal, workout, steps));
        }



        adapter = new ProgressListAdapter(getApplicationContext(), mProgressList);
        traineeList.setAdapter(adapter);

        traineeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something if needed
            }
        });
//        traineeList.setAdapter(arrayAdapter);
//        if (traineeList.getCount() == 0){
//            traineeList.setEmptyView(emptyList);
//            emptyList.setText("LIST IS EMPTY");
//        }

    }
}
