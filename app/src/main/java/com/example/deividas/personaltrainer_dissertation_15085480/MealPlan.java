package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MealPlan extends AppCompatActivity {
    TextView usernameDashboard;
    DatabaseHelper db;
    private String username;

    TextView breakfast1;
    TextView breakfast2;
    TextView breakfast3;
    TextView breakfast_notes;
    TextView lunch1;
    TextView lunch2;
    TextView lunch3;
    TextView lunch_notes;
    TextView dinner1;
    TextView dinner2;
    TextView dinner3;
    TextView dinner_notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

        //Displaying name on dashboard from DB
        usernameDashboard = findViewById(R.id.username_dashboard);
        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");
        Cursor cursor = db.retrieveData(username);
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA ABOUT THIS USER", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                usernameDashboard.setText(cursor.getString(1) + " " + cursor.getString(2));
            }
        }
        //End of displaying name from DB

        //Display
        Cursor cursor1 = db.retrieveDataMealPlans(username);
        breakfast1 = findViewById(R.id.tv_breakfast1);
        breakfast2 = findViewById(R.id.tv_breakfast2);
        breakfast3 = findViewById(R.id.tv_breakfast3);
        breakfast_notes = findViewById(R.id.tv_breakfast4);
        lunch1 = findViewById(R.id.tv_lunch1);
        lunch2 = findViewById(R.id.tv_lunch2);
        lunch3 = findViewById(R.id.tv_lunch3);
        lunch_notes = findViewById(R.id.tv_lunch4);
        dinner1 = findViewById(R.id.tv_dinner1);
        dinner2 = findViewById(R.id.tv_dinner2);
        dinner3 = findViewById(R.id.tv_dinner3);
        dinner_notes = findViewById(R.id.tv_dinner4);
        if (cursor1.getCount() > 0){
            cursor1.getColumnNames();
            while (cursor1.moveToNext()){
                //BREAKFAST
                if (cursor1.getString(1) == null){
                    breakfast1.setText("NO BREAKFAST OPTION 1");
                }
                else{
                    breakfast1.setText(cursor1.getString(1));
                }
                if (cursor1.getString(2) == null){
                    breakfast2.setText("NO BREAKFAST OPTION 2");
                }
                else{
                    breakfast2.setText(cursor1.getString(2));
                }
                if (cursor1.getString(3) == null){
                    breakfast3.setText("NO BREAKFAST OPTION 3");
                }
                else{
                    breakfast3.setText(cursor1.getString(3));
                }
                if (cursor1.getString(4) == null){
                    breakfast_notes.setText("NO REMINDERS FOR BREAKFAST");
                }
                else{
                    breakfast_notes.setText(cursor1.getString(4));
                }

                //LUNCH
                if (cursor1.getString(5) == null){
                    lunch1.setText("NO LUNCH OPTION 1");
                }
                else{
                    lunch1.setText(cursor1.getString(5));
                }
                if (cursor1.getString(6) == null){
                    lunch2.setText("NO LUNCH OPTION 2");
                }
                else{
                    lunch2.setText(cursor1.getString(6));
                }
                if (cursor1.getString(7) == null){
                    lunch3.setText("NO LUNCH OPTION 3");
                }
                else{
                    lunch3.setText(cursor1.getString(7));
                }
                if (cursor1.getString(8) == null){
                    lunch_notes.setText("NO REMINDERS FOR LUNCH");
                }
                else{
                    lunch_notes.setText(cursor1.getString(8));
                }

                //DINNER
                if (cursor1.getString(9) == null){
                    dinner1.setText("NO DINNER OPTION 1");
                }
                else{
                    dinner1.setText(cursor1.getString(9));
                }
                if (cursor1.getString(10) == null){
                    dinner2.setText("NO DINNER OPTION 2");
                }
                else{
                    dinner2.setText(cursor1.getString(10));
                }
                if (cursor1.getString(11) == null){
                    dinner3.setText("NO DINNER OPTION 3");
                }
                else{
                    dinner3.setText(cursor1.getString(11));
                }
                if (cursor1.getString(12) == null){
                    dinner_notes.setText("NO REMINDERS FOR DINNER");
                }
                else{
                    dinner_notes.setText(cursor1.getString(12));
                }
            }
        }
        //End of meals display
    }
}
