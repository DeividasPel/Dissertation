package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView register;
    private Button login;
    private EditText email, password;
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //REGISTER
        register = (TextView) findViewById(R.id.create_account);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        //LOGIN
        myDB = new DatabaseHelper(this);
        email = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);
        login = (Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email_str = email.getText().toString();
               String password_str = password.getText().toString();
               Boolean check_emailpassword = myDB.checkEmailPassword(email_str, password_str);
               if (check_emailpassword == true){
                   Toast.makeText(getApplicationContext(), "Succesfully logged in", Toast.LENGTH_SHORT).show();
                   openTrainee();
               }
               else{
                   Toast.makeText(getApplicationContext(), "Wrong e-mail or password", Toast.LENGTH_SHORT).show();
               }
            }
        });

    }

    //TO OPEN NET ACTIVITY REGISTER
    public void openRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    //TO OPEN TRAINEE ACTIVITY
    public void openTrainee(){
        Intent intent = new Intent(this, TraineeActivity.class);
        intent.putExtra("username", email.getText().toString());
        startActivity(intent);
    }


}
