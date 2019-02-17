package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Workout extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView workouts;
    private ArrayList<WorkoutType> wodList;
    private ArrayList<String> titleList;
    private Adapter adapter;
    private TextView usernameDashboard;
    DatabaseHelper db;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        db = new DatabaseHelper(this);
        usernameDashboard = findViewById(R.id.username_dashboard);
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
        //End

        workouts = findViewById(R.id.lv_workouts);

        wodList = DatabaseHelper.loadWorkout(this);
        titleList = new ArrayList<>();
        for (int i = 0; i < wodList.size(); i++){
            String str = wodList.get(i).getTitle();
            titleList.add(str);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList);
        workouts.setAdapter((ListAdapter) adapter);

        workouts.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, WorkoutDetail.class);
        String title = wodList.get(position).getTitle();
        String wod = wodList.get(position).getWod();

        intent.putExtra("EXTRA_TITLE", title);
        intent.putExtra("EXTRA_WOD", wod);

        startActivity(intent);
    }
}
