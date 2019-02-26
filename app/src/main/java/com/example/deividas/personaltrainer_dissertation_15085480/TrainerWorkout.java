package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class TrainerWorkout extends AppCompatActivity{
    private TextView usernameDashboard, traineeDashboard;
    String trainerName, traineeName, traineeEmail;
    private ListView workouts;
    private ArrayList<WorkoutType> wodList;
    private ArrayList<String> titleList, workoutList = new ArrayList<>();
    private Adapter adapter;
    DatabaseHelper db;
    Button btnWorkouts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_workout);

        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        traineeEmail = getIntent().getStringExtra("traineeEmail");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        db = new DatabaseHelper(this);

        workouts = findViewById(R.id.lv_workout_trainer);
        workouts.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        wodList = db.loadWorkout(this);
        titleList = new ArrayList<>();
        for (int i = 0; i < wodList.size(); i++){
            String str = wodList.get(i).getTitle();
            titleList.add(str);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.txt_lan, titleList);
        workouts.setAdapter((ListAdapter) adapter);


        workouts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView)view).getText().toString();
                if(workoutList.contains(selectedItem)){
                    workoutList.remove(selectedItem);
                }
                else{
                    workoutList.add(selectedItem);
                }
            }
        });

        btnWorkouts = findViewById(R.id.btn_workouts_trainer);
        btnWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(String item:workoutList){
                    db.insertNewWorkout(traineeEmail, item);
                    db.updateWorkoutStatus("0", traineeEmail);
                    Toast.makeText(getApplicationContext(),"Workouts have been added!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void showSelectedItems(View view){
        String items="";
        for (String item:workoutList){
            items+="-"+item+"\n";
        }
        Toast.makeText(this,"You have added \n" + items,Toast.LENGTH_LONG).show();
    }
    //@Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        workoutList.add(wodList.get(position).getTitle());
//        //db.insertNewWorkout(traineeEmail, wodList.get(position).getTitle());
//    }



}
