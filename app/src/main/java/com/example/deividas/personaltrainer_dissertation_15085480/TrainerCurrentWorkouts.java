package com.example.deividas.personaltrainer_dissertation_15085480;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrainerCurrentWorkouts extends AppCompatActivity {
    private TextView usernameDashboard, traineeDashboard;
    DatabaseHelper db;
    String trainerName, traineeName, traineeEmail;
    private ListView workouts;
    private ArrayList<WorkoutType> wodList;
    private ArrayList<String> titleList, workoutsList, workoutsListSub;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_current_workouts);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        traineeEmail = getIntent().getStringExtra("traineeEmail");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        workouts = findViewById(R.id.lv_workouts);
        workoutsList = db.getWorkoutsList(traineeEmail);
        workoutsListSub = new ArrayList<>();

        wodList = DatabaseHelper.loadWorkout(this);
        titleList = new ArrayList<>();
        for (int i = 0; i < wodList.size(); i++){
            String str = wodList.get(i).getTitle();
            for (int j = 0; j < workoutsList.size(); j++){
                if (workoutsList.get(j).equals(str)){
                    titleList.add(str);
                    workoutsListSub.add(wodList.get(i).getWod());
                }
            }
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList);
        workouts.setAdapter((ListAdapter) adapter);
    }
}
