package com.example.deividas.personaltrainer_dissertation_15085480;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText dob; //for date of birth
    private EditText name, surname, dateofbirth, height, weight, email, password1, password2, goal, training, notes; //registration form to be sent to DB
    private Spinner gender,trainer;
    private Button register, button_more;
    private DatePickerDialog.OnDateSetListener dobSetListener;
    DatabaseHelper myDB;
    private ArrayList<String> gender_list = new ArrayList<>();
    private ArrayList<String> trainer_list = new ArrayList<>();
    private Spinner gender_spinner;
    private Spinner trainer_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //DATE OF BIRTH
        dob = (EditText) findViewById(R.id.et_dob);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Register.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, dobSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dobSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;
                dob.setText(date);
            }
        };
        //END OF DATE OR BIRTH

        //List of genders
        gender_list.add("Male");
        gender_list.add("Female");
        gender_list.add("Other");
        gender_spinner = findViewById(R.id.et_gender);

        HintSpinner<String> hintSpinner_gender = new HintSpinner<>(gender_spinner, new HintAdapter<>(this, R.string.gender_hint, gender_list), new HintSpinner.Callback<String>() {
                    @Override
                    public void onItemSelected(int position, String itemAtPosition) {
                        //
                    }
                });
        hintSpinner_gender.init();

        //List of trainers
        trainer_list.add("Bob Champion");
        trainer_list.add("Arantza Aldea");
        trainer_list.add("Mark Green");
        trainer_spinner = findViewById(R.id.et_trainer);

        button_more = findViewById(R.id.button_more);
        button_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainers();
            }
        });

        HintSpinner<String> hintSpinner_trainer = new HintSpinner<>(trainer_spinner, new HintAdapter<>(this, R.string.trainer_hint, trainer_list), new HintSpinner.Callback<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                //
            }
        });
        hintSpinner_trainer.init();

        //DATABASE SQLITE
        myDB = new DatabaseHelper(this);
        name = (EditText)findViewById(R.id.et_name);
        surname = (EditText)findViewById(R.id.et_surname);
        dateofbirth = (EditText)findViewById(R.id.et_dob);
        height = (EditText)findViewById(R.id.et_height);
        gender = (Spinner) findViewById(R.id.et_gender);
        weight = (EditText)findViewById(R.id.et_weight);
        email = (EditText)findViewById(R.id.et_email);
        password1 = (EditText)findViewById(R.id.et_password1);
        password2 = (EditText)findViewById(R.id.et_password2);
        goal = (EditText)findViewById(R.id.et_goal);
        training = (EditText)findViewById(R.id.et_training);
        notes = (EditText)findViewById(R.id.et_notes);
        trainer = (Spinner) findViewById(R.id.et_trainer);
        register = (Button)findViewById(R.id.btn_finish);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name = name.getText().toString();
                String str_surname = surname.getText().toString();
                String str_dateofbirth = dateofbirth.getText().toString();
                String str_height = height.getText().toString();
                String str_gender = gender.getSelectedItem().toString();
                String str_weight = weight.getText().toString();
                String str_email = email.getText().toString();
                String str_password1 = password1.getText().toString();
                String str_password2 = password2.getText().toString();
                String str_goal = goal.getText().toString();
                String str_training = training.getText().toString();
                String str_notes = notes.getText().toString();
                String str_trainer = trainer.getSelectedItem().toString();
                if (str_name.equals("") || str_surname.equals("") || str_dateofbirth.equals("") || str_height.equals("") || str_gender.equals("") || str_weight.equals("") || str_email.equals("") || str_password1.equals("") || str_password2.equals("") || str_goal.equals("") || str_training.equals("") || str_notes.equals("") || str_trainer.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (str_password1.equals(str_password2)){
                        Boolean check = myDB.checkEmail(str_email);
                        if (check == true){
                            Boolean insert = myDB.insert(str_name, str_surname, str_dateofbirth, str_height, str_gender, str_weight, str_email, str_password1, str_goal, str_training, str_notes, str_trainer, null, null, null, null, null, null, null, null, null, null, null, null, "0", "0", "10000");
                            if (insert == true){
                                Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                openMain();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //END OF DB
}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        if (position > 0) {
            // Notify the selected item text
            Toast.makeText(getApplicationContext(), "Selected : " + text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openTrainers(){
        Intent intent = new Intent(this, Trainers.class);
        startActivity(intent);
    }



}
