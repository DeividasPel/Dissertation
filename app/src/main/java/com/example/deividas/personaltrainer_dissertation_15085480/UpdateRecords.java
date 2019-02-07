package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateRecords extends AppCompatActivity {
    private TextView usernameDashboard;
    DatabaseHelper db;
    private String username;
    private EditText weight, goal, training, notes;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_records);
        //Retrieve information from DB about current user
        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");
        Cursor cursor = db.retrieveData(username);

        //Displaying information retrieved from DB
        usernameDashboard = findViewById(R.id.username_dashboard);
        weight = findViewById(R.id.et_weight_records);
        goal = findViewById(R.id.et_goal_records);
        training = findViewById(R.id.et_training_records);
        notes = findViewById(R.id.et_notes_records);
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA ABOUT THIS USER", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                usernameDashboard.setText(cursor.getString(1) + " " + cursor.getString(2));
                weight.setText(cursor.getString(6), TextView.BufferType.EDITABLE);
                goal.setText(cursor.getString(9), TextView.BufferType.EDITABLE);
                training.setText(cursor.getString(10), TextView.BufferType.EDITABLE);
                notes.setText(cursor.getString(11), TextView.BufferType.EDITABLE);
            }
        }
        //End of displaying name from DB

        //Updating records
        update = findViewById(R.id.btn_update_records);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightUpdate = weight.getText().toString();
                String goalUpdate = goal.getText().toString();
                String trainingUpdate = training.getText().toString();
                String notesUpdate = notes.getText().toString();
                if (weightUpdate.matches("")){
                    weight.setText("No information");
                    weightUpdate = ("No information");
                }
                if (goalUpdate.matches("")){
                    goal.setText("No information");
                    goalUpdate = ("No information");
                }
                if (trainingUpdate.matches("")){
                    training.setText("No information");
                    trainingUpdate = ("No information");
                }
                if (notesUpdate.matches("")){
                    notes.setText("No information");
                    notesUpdate = ("No information");
                }
                Boolean updateRecords = db.updateRecordsTrainee(weightUpdate, goalUpdate, trainingUpdate, notesUpdate, username);
                Toast.makeText(getApplicationContext(), "Records successfully updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
