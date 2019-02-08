package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Messages extends AppCompatActivity {
    private TextView usernameDashboard;
    private ListView messages;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        String username = getIntent().getStringExtra("username");
        db = new DatabaseHelper(this);

        //Displaying name on dashboard from DB
        usernameDashboard = findViewById(R.id.username_dashboard_messages);
        Cursor cursor = db.retrieveData(username);
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA ABOUT THIS USER", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                usernameDashboard.setText(cursor.getString(1) + " " + cursor.getString(2));
            }
        }
        //End of displaying name from DB

        //display messages on list view
        messages = findViewById(R.id.lv_messages);
        ArrayList<String> arrayList = db.getMessagesList(username);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        messages.setAdapter(arrayAdapter);

    }
}
