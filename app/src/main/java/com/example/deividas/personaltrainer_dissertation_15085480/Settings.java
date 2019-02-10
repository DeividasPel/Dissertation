package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements SettingsNameDialog.ExampleDialogListener {
    LinearLayout nameSettings, surnameSettings;
    private TextView usernameDashboard, name, surname, dob, gender, email, password;
    DatabaseHelper db;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        name = findViewById(R.id.tv_name_settings);
        nameSettings = findViewById(R.id.tv_name_settings_button);
        surname = findViewById(R.id.tv_surname_settings);
        surnameSettings = findViewById(R.id.tv_surname_settings_button);
        dob = findViewById(R.id.tv_dob_settings);
        gender = findViewById(R.id.tv_gender_settings);
        email = findViewById(R.id.tv_email_settings);
        password = findViewById(R.id.tv_password_settings);

        //Displaying properties retrieved from database
        usernameDashboard = (TextView) findViewById(R.id.username_dashboard);
        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");
        Cursor cursor = db.retrieveData(username);
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA ABOUT THIS USER", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                usernameDashboard.setText(cursor.getString(1) + " " + cursor.getString(2));
                name.setText(cursor.getString(1));
                surname.setText(cursor.getString(2));
                dob.setText(cursor.getString(3));
                gender.setText(cursor.getString(5));
                email.setText(cursor.getString(7));
            }
        }
        //End of displaying properties from DB

        //Edit name
        nameSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        //End of edit name


    }

    public void openDialog() {
        SettingsNameDialog exampleDialog = new SettingsNameDialog();
        exampleDialog.show(getSupportFragmentManager(), "Name");
    }

    @Override
    public void applyTexts(String nameSettings) {
        name.setText(nameSettings);
    }
}
