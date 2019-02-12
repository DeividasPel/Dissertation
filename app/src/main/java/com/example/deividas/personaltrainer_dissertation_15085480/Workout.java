package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Workout extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView workouts;
    private ArrayList<WorkoutType> wodList;
    private ArrayList<String> titleList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

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
