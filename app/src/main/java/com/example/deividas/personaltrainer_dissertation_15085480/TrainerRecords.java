package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TrainerRecords extends AppCompatActivity {
    private TextView name, surname, dateOfBirth, gender, email, height, weight, goal, training, notes, usernameDashboard, traineeDashboard;
    DatabaseHelper db;
    String trainerName, traineeName, traineeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_records);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        traineeEmail = getIntent().getStringExtra("traineeEmail");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        name = findViewById(R.id.tv_name_records_trainer);
        surname = findViewById(R.id.tv_surname_records_trainer);
        dateOfBirth = findViewById(R.id.tv_dob_records_trainer);
        gender = findViewById(R.id.tv_gender_records_trainer);
        email = findViewById(R.id.tv_email_records_trainer);
        height = findViewById(R.id.tv_height_records_trainer);
        weight = findViewById(R.id.tv_weight_records_trainer);
        goal = findViewById(R.id.tv_goal_records_trainer);
        training = findViewById(R.id.tv_training_records_trainer);
        notes = findViewById(R.id.tv_notes_records_trainer);

        Cursor cursor = db.retrieveData(traineeEmail);
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA ABOUT THIS USER", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                name.setText(cursor.getString(1));
                surname.setText(cursor.getString(2));
                dateOfBirth.setText(cursor.getString(3));
                gender.setText(cursor.getString(5));
                email.setText(cursor.getString(7));
                height.setText(cursor.getString(4));
                weight.setText(cursor.getString(6));
                goal.setText(cursor.getString(9));
                training.setText(cursor.getString(10));
                notes.setText(cursor.getString(11));
            }
        }
    }
}
