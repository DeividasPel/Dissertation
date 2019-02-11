package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements SettingsNameDialog.ExampleDialogListener, SettingsSurnameDialog.ExampleDialogListener, SettingsDobDialog.ExampleDialogListener{
    LinearLayout nameSettings, surnameSettings, dobSettings, genderSettings;
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
        dobSettings = findViewById(R.id.tv_dob_settings_button);
        gender = findViewById(R.id.tv_gender_settings);
        genderSettings = findViewById(R.id.tv_gender_settings_button);
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
                openDialogName();
            }
        });
        //End of edit name

        //Edit surname
        surnameSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogSurname();
            }
        });
        //End of edit surname

        //Edit dob
        dobSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogDob();
            }
        });
        //End of edit dob

        //Edit gender
        genderSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //End of edit gender


    }

    public void openDialogName() {
        SettingsNameDialog Dialog = new SettingsNameDialog();
        Dialog.show(getSupportFragmentManager(), "Change name");
    }

    public void openDialogSurname() {
        SettingsSurnameDialog Dialog = new SettingsSurnameDialog();
        Dialog.show(getSupportFragmentManager(), "Change surname");
    }

    public void openDialogDob() {
        SettingsDobDialog Dialog = new SettingsDobDialog();
        Dialog.show(getSupportFragmentManager(), "Change Date of Birth");
    }

    @Override
    public void applyTextName(String nameSettings) {
        this.name.setText(nameSettings);
    }

    @Override
    public void applyTextSurname(String surnameSettings) {
        this.surname.setText(surnameSettings);
    }

    @Override
    public void applyTextDob(String dateOfBirth) {
        this.dob.setText(dateOfBirth);
    }
}
