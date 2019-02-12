package com.example.deividas.personaltrainer_dissertation_15085480;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WorkoutDetail extends AppCompatActivity {

    private TextView title;
    private TextView wod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        title = findViewById(R.id.tv_title);
        wod = findViewById(R.id.tv_wod);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            String t = extra.getString("EXTRA_TITLE");
            String w = extra.getString("EXTRA_WOD");

            title.setText(t);
            wod.setText(w);
        }

    }
}
