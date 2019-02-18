package com.example.deividas.personaltrainer_dissertation_15085480;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TrainerMealPlan extends AppCompatActivity {

    DatabaseHelper db;
    String trainerName, traineeName, traineeEmail;
    private TextView usernameDashboard, traineeDashboard;
    private EditText breakfast1, breakfast2, breakfast3, breakfastNotes, lunch1, lunch2, lunch3, lunchNotes, dinner1, dinner2, dinner3, dinnerNotes;
    private Button clearAll, updateMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_meal_plan);

        db = new DatabaseHelper(this);

        //Display trainer name on dashboard and trainee name
        trainerName = getIntent().getStringExtra("trainer");
        traineeName = getIntent().getStringExtra("trainee");
        traineeEmail = getIntent().getStringExtra("traineeEmail");
        usernameDashboard = findViewById(R.id.username_dashboard_trainer);
        traineeDashboard = findViewById(R.id.tv_trainee);
        usernameDashboard.setText(trainerName);
        traineeDashboard.setText(traineeName);

        //Adding meals
        breakfast1 = findViewById(R.id.et_breakfast1_trainer);
        breakfast2 = findViewById(R.id.et_breakfast2_trainer);
        breakfast3 = findViewById(R.id.et_breakfast3_trainer);
        breakfastNotes = findViewById(R.id.et_breakfast_notes_trainer);
        lunch1 = findViewById(R.id.et_lunch1_trainer);
        lunch2 = findViewById(R.id.et_lunch2_trainer);
        lunch3 = findViewById(R.id.et_lunch3_trainer);
        lunchNotes = findViewById(R.id.et_lunch_notes_trainer);
        dinner1 = findViewById(R.id.et_dinner1_trainer);
        dinner2 = findViewById(R.id.et_dinner2_trainer);
        dinner3 = findViewById(R.id.et_dinner3_trainer);
        dinnerNotes = findViewById(R.id.et_dinner_notes_trainer);

        Cursor cursor1 = db.retrieveDataMealPlans(traineeEmail);
        if (cursor1.getCount() > 0){
            cursor1.getColumnNames();
            while (cursor1.moveToNext()){
                //BREAKFAST
                if (cursor1.getString(1) == null){
                    breakfast1.setText("No information", TextView.BufferType.EDITABLE);
                }
                else{
                    breakfast1.setText(cursor1.getString(1), TextView.BufferType.EDITABLE);
                }
                if (cursor1.getString(2) == null){
                    breakfast2.setText("No information", TextView.BufferType.EDITABLE);
                }
                else{
                    breakfast2.setText(cursor1.getString(2), TextView.BufferType.EDITABLE);
                }
                if (cursor1.getString(3) == null){
                    breakfast3.setText("No information", TextView.BufferType.EDITABLE);
                }
                else{
                    breakfast3.setText(cursor1.getString(3), TextView.BufferType.EDITABLE);
                }
                if (cursor1.getString(4) == null){
                    breakfastNotes.setText("No messages for breakfast", TextView.BufferType.EDITABLE);
                }
                else{
                    breakfastNotes.setText(cursor1.getString(4), TextView.BufferType.EDITABLE);
                }

                //LUNCH
                if (cursor1.getString(5) == null){
                    lunch1.setText("No information", TextView.BufferType.EDITABLE);
                }
                else{
                    lunch1.setText(cursor1.getString(5), TextView.BufferType.EDITABLE);
                }
                if (cursor1.getString(6) == null){
                    lunch2.setText("No information", TextView.BufferType.EDITABLE);
                }
                else{
                    lunch2.setText(cursor1.getString(6), TextView.BufferType.EDITABLE);
                }
                if (cursor1.getString(7) == null){
                    lunch3.setText("No information", TextView.BufferType.EDITABLE);
                }
                else{
                    lunch3.setText(cursor1.getString(7), TextView.BufferType.EDITABLE);
                }
                if (cursor1.getString(8) == null){
                    lunchNotes.setText("No messages for lunch", TextView.BufferType.EDITABLE);
                }
                else{
                    lunchNotes.setText(cursor1.getString(8), TextView.BufferType.EDITABLE);
                }

                //DINNER
                if (cursor1.getString(9) == null){
                    dinner1.setText("No information", TextView.BufferType.EDITABLE);
                }
                else{
                    dinner1.setText(cursor1.getString(9), TextView.BufferType.EDITABLE);
                }
                if (cursor1.getString(10) == null){
                    dinner2.setText("No information", TextView.BufferType.EDITABLE);
                }
                else{
                    dinner2.setText(cursor1.getString(10), TextView.BufferType.EDITABLE);
                }
                if (cursor1.getString(11) == null){
                    dinner3.setText("No information", TextView.BufferType.EDITABLE);
                }
                else{
                    dinner3.setText(cursor1.getString(11), TextView.BufferType.EDITABLE);
                }
                if (cursor1.getString(12) == null){
                    dinnerNotes.setText("No messages for dinner", TextView.BufferType.EDITABLE);
                }
                else{
                    dinnerNotes.setText(cursor1.getString(12), TextView.BufferType.EDITABLE);
                }
            }
        }
        clearAll = findViewById(R.id.btn_clear_meals);
        updateMeals = findViewById(R.id.btn_update_meal_plan);
        updateMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getBreakfast1 = breakfast1.getText().toString();
                String getBreakfast2 = breakfast2.getText().toString();
                String getBreakfast3 = breakfast3.getText().toString();
                String getBreakfastNotes = breakfastNotes.getText().toString();
                String getLunch1 = lunch1.getText().toString();
                String getLunch2 = lunch2.getText().toString();
                String getLunch3 = lunch3.getText().toString();
                String getLunchNotes = lunchNotes.getText().toString();
                String getDinner1 = dinner1.getText().toString();
                String getDinner2 = dinner2.getText().toString();
                String getDinner3 = dinner3.getText().toString();
                String getDinnerNotes = dinnerNotes.getText().toString();
                if (getBreakfast1.matches("")){
                    getBreakfast1 = "NO BREAKFAST OPTION 1";
                }
                if (getBreakfast2.matches("")){
                    getBreakfast2 = "NO BREAKFAST OPTION 2";
                }
                if (getBreakfast3.matches("")){
                    getBreakfast3 = "NO BREAKFAST OPTION 3";
                }
                if (getBreakfastNotes.matches("")){
                    getBreakfastNotes = "NO REMINDERS FOR BREAKFAST";
                }
                if (getLunch1.matches("")){
                    getLunch1 = "NO LUNCH OPTION 1";
                }
                if (getLunch2.matches("")){
                    getLunch2 = "NO LUNCH OPTION 2";
                }
                if (getLunch3.matches("")){
                    getLunch3 = "NO LUNCH OPTION 3";
                }
                if (getLunchNotes.matches("")){
                    getLunchNotes = "NO REMINDERS FOR LUNCH";
                }
                if (getDinner1.matches("")){
                    getDinner1 = "NO DINNER OPTION 1";
                }
                if (getDinner2.matches("")){
                    getDinner2 = "NO DINNER OPTION 2";
                }
                if (getDinner3.matches("")){
                    getDinner3 = "NO DINNER OPTION 3";
                }
                if (getDinnerNotes.matches("")){
                    getDinnerNotes = "NO REMINDERS FOR DINNER";
                }
                db.updateMealPlan(getBreakfast1, getBreakfast2, getBreakfast3, getBreakfastNotes, getLunch1, getLunch2, getLunch3, getLunchNotes, getDinner1, getDinner2, getDinner3, getDinnerNotes, traineeEmail);
                Toast.makeText(getApplicationContext(), "Meal plan updated successfully", Toast.LENGTH_SHORT).show();
            }
        });
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakfast1.getText().clear();
                breakfast2.getText().clear();
                breakfast3.getText().clear();
                breakfastNotes.getText().clear();
                lunch1.getText().clear();
                lunch2.getText().clear();
                lunch3.getText().clear();
                lunchNotes.getText().clear();
                dinner1.getText().clear();
                dinner2.getText().clear();
                dinner3.getText().clear();
                dinnerNotes.getText().clear();
                Toast.makeText(getApplicationContext(), "All meals erased successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
