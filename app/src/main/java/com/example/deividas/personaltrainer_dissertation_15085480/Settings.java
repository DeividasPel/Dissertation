package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements SettingsNameDialog.ExampleDialogListener, SettingsSurnameDialog.ExampleDialogListener, SettingsDobDialog.ExampleDialogListener, SettingsGenderDialog.ExampleDialogListener, SettingsEmailDialog.ExampleDialogListener, SettingsPasswordDialog.ExampleDialogListener{
    LinearLayout nameSettings, surnameSettings, dobSettings, genderSettings, emailSettings, passwordSettings;
    private TextView usernameDashboard, name, surname, dob, gender, email, password;
    DatabaseHelper db;
    private String username;
    private Button update;

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
        emailSettings = findViewById(R.id.tv_email_settings_button);
        password = findViewById(R.id.tv_password_settings);
        passwordSettings = findViewById(R.id.tv_password_settings_button);
        update = findViewById(R.id.btn_update_settings);

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
                password.setText(cursor.getString(8));
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
                openDialogGender();
            }
        });
        //End of edit gender

        //Edit email
        emailSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogEmail();
            }
        });
        //End of edit email

        //Edit password
        passwordSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogPassword();
            }
        });
        //End of edit password

        //Update records
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString();
                String newSurname = surname.getText().toString();
                String newDob = dob.getText().toString();
                String newGender = gender.getText().toString();
                String newEmail = email.getText().toString();
                String newPassword1 = password.toString();
                Log.d("MyApp",newPassword1);
                Log.d("MyApp",newEmail);
                String newPassword = "123456";

                Boolean updateSettings = db.updateSettingsTrainee(newName, newSurname, newDob, newGender, newEmail, newPassword, username);

                if (updateSettings == true){
                    Toast.makeText(getApplicationContext(), "Settings successfully updated. Please log in again", Toast.LENGTH_SHORT).show();
                    openMain();
                }
                else{
                    Toast.makeText(getApplicationContext(), "There was a problem updating your settings", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    public void openDialogGender() {
        SettingsGenderDialog Dialog = new SettingsGenderDialog();
        Dialog.show(getSupportFragmentManager(), "Change Gender");
    }

    public void openDialogEmail() {
        SettingsEmailDialog Dialog = new SettingsEmailDialog();
        Dialog.show(getSupportFragmentManager(), "Change E-mail");
    }

    public void openDialogPassword() {
        SettingsPasswordDialog Dialog = new SettingsPasswordDialog();
        Dialog.show(getSupportFragmentManager(), "Change Password");
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

    @Override
    public void applyTextGender(String gender) {
        this.gender.setText(gender);
    }

    @Override
    public void applyTextEmail(String email) {
        this.email.setText(email);
    }

    @Override
    public void applyTextPassword(String password) {
        this.password.setText(password);
    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
