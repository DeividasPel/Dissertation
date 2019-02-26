package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user7.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "SURNAME";
    public static final String COL_3 = "DOB";
    public static final String COL_4 = "HEIGHT";
    public static final String COL_5 = "GENDER";
    public static final String COL_6 = "WEIGHT";
    public static final String COL_7 = "EMAIL";
    public static final String COL_8 = "PASSWORD";
    public static final String COL_9 = "GOAL";
    public static final String COL_10 = "TRAINING";
    public static final String COL_11 = "NOTES";
    public static final String COL_12 = "TRAINER";
    public static final String STEPS_TAKEN = "STEPS";
    public static final String CALORIES_BURNED = "CALORIES";
    public static final String STEPS_GOAL = "STEPGOAL";
    public static final String WORKOUT_STATUS = "WORKOUT_STATUS"; //0 NOT FINISHED - 1 FINISHED
    public static final String MEAL_STATUS = "MEAL_STATUS"; //0 NOT FINISHED - 1 FINISHED

    //MEAL PLAN DB
    public static final String TABLE_NAME_MEAL_PLAN = "meal_plan";
    public static final String COL_13 = "BREAKFAST1";
    public static final String COL_14 = "BREAKFAST2";
    public static final String COL_15 = "BREAKFAST3";
    public static final String COL_16 = "BREAKFAST_NOTES";
    public static final String COL_17 = "LUNCH1";
    public static final String COL_18 = "LUNCH2";
    public static final String COL_19 = "LUNCH3";
    public static final String COL_20 = "LUNCH_NOTES";
    public static final String COL_21 = "DINNER1";
    public static final String COL_22 = "DINNER2";
    public static final String COL_23 = "DINNER3";
    public static final String COL_24 = "DINNER_NOTES";

    //MESSAGES DB
    public static final String TABLE_NAME_MESSAGES = "user_message";
    public static final String COL_25 = "MESSAGE";

    //WORKOUTS DB
    public static final String TABLE_NAME_WORKOUTS = "user_workouts";
    public static final String COL_26 = "WORKOUT";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, DOB TEXT, HEIGHT TEXT, GENDER TEXT, WEIGHT TEXT, EMAIL TEXT, PASSWORD TEXT, GOAL TEXT, TRAINING TEXT, NOTES TEXT, TRAINER TEXT, STEPS TEXT, CALORIES TEXT, STEPGOAL TEXT, WORKOUT_STATUS TEXT, MEAL_STATUS TEXT) ");
        db.execSQL("Create table " + TABLE_NAME_MEAL_PLAN + "(EMAIL TEXT, BREAKFAST1 TEXT, BREAKFAST2 TEXT, BREAKFAST3 TEXT, BREAKFAST_NOTES TEXT, LUNCH1 TEXT, LUNCH2 TEXT, LUNCH3 TEXT, LUNCH_NOTES TEXT, DINNER1 TEXT, DINNER2 TEXT, DINNER3 TEXT, DINNER_NOTES TEXT)");
        db.execSQL("Create table " + TABLE_NAME_MESSAGES + "(EMAIL TEXT, MESSAGE TEXT)");
        db.execSQL("Create table " + TABLE_NAME_WORKOUTS + "(EMAIL TEXT, WORKOUT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEAL_PLAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MESSAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_WORKOUTS);
        onCreate(db);
    }

    //inserting to DB
    public boolean insert (String name, String surname, String dob, String height, String gender, String weight, String email, String password, String goal, String training, String notes, String trainer, String breakfast1, String breakfast2, String breakfast3, String breakfast_notes, String lunch1, String lunch2, String lunch3, String lunch_notes, String dinner1, String dinner2, String dinner3, String dinner_notes, String steps, String calories, String stepGoal, String workoutStatus, String mealStatus){
       SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, surname);
        contentValues.put(COL_3, dob);
        contentValues.put(COL_4, height);
        contentValues.put(COL_5, gender);
        contentValues.put(COL_6, weight);
        contentValues.put(COL_7, email);
        contentValues.put(COL_8, password);
        contentValues.put(COL_9, goal);
        contentValues.put(COL_10, training);
        contentValues.put(COL_11, notes);
        contentValues.put(COL_12, trainer);
        contentValues.put(STEPS_TAKEN, steps);
        contentValues.put(CALORIES_BURNED, calories);
        contentValues.put(STEPS_GOAL, stepGoal);
        contentValues.put(WORKOUT_STATUS, workoutStatus);
        contentValues.put(MEAL_STATUS, mealStatus);
        db.insert("user_table", null, contentValues);

        ContentValues contentValuesMealPlan = new ContentValues();
        contentValuesMealPlan.put(COL_7, email);
        contentValuesMealPlan.put(COL_13, breakfast1);
        contentValuesMealPlan.put(COL_14, breakfast2);
        contentValuesMealPlan.put(COL_15, breakfast3);
        contentValuesMealPlan.put(COL_16, breakfast_notes);
        contentValuesMealPlan.put(COL_17, lunch1);
        contentValuesMealPlan.put(COL_18, lunch2);
        contentValuesMealPlan.put(COL_19, lunch3);
        contentValuesMealPlan.put(COL_20, lunch_notes);
        contentValuesMealPlan.put(COL_21, dinner1);
        contentValuesMealPlan.put(COL_22, dinner2);
        contentValuesMealPlan.put(COL_23, dinner3);
        contentValuesMealPlan.put(COL_24, dinner_notes);
        db.insert("meal_plan", null, contentValuesMealPlan);

        return true;
    }

    //checking if email exists
    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from user_table where email=?", new String[] {email});
        if (cursor.getCount() > 0 ){
            //Log.i("David1996", cursor.getString(1));
            return false;
        }
        else{
            return true;
        }
    }

    //checking if email and password correct
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    //retrieving data about particular user
    public Cursor retrieveData(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where email=?", new String[]{email});
        return cursor;
    }

    //retrieving meal plans
    public Cursor retrieveDataMealPlans(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from meal_plan where email=?", new String[]{email});
        return cursor;
    }

    //add meal plan (by trainer)
    public boolean updateMealPlan(String breakfast1, String breakfast2, String breakfast3, String breakfast_notes, String lunch1, String lunch2, String lunch3, String lunch_notes, String dinner1, String dinner2, String dinner3, String dinner_notes, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_13, breakfast1);
        contentValues.put(COL_14, breakfast2);
        contentValues.put(COL_15, breakfast3);
        contentValues.put(COL_16, breakfast_notes);
        contentValues.put(COL_17, lunch1);
        contentValues.put(COL_18, lunch2);
        contentValues.put(COL_19, lunch3);
        contentValues.put(COL_20, lunch_notes);
        contentValues.put(COL_21, dinner1);
        contentValues.put(COL_22, dinner2);
        contentValues.put(COL_23, dinner3);
        contentValues.put(COL_24, dinner_notes);
        db.update("meal_plan", contentValues, "email=?", new String[]{email});
        return true;
    }

    //update records (for trainee)
    public boolean updateRecordsTrainee(String weight, String goal, String training, String notes, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6, weight);
        contentValues.put(COL_9, goal);
        contentValues.put(COL_10, training);
        contentValues.put(COL_11, notes);
        db.update("user_table", contentValues, "email=?", new String[]{email});
        return true;
    }

    //update settings (for trainee)
    public boolean updateSettingsTrainee(String name, String settings, String dob, String gender, String newEmail, String password, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, settings);
        contentValues.put(COL_3, dob);
        contentValues.put(COL_5, gender);
        contentValues.put(COL_7, newEmail);
        contentValues.put(COL_8, password);
        db.update("user_table", contentValues, "email=?", new String[]{email});
        return true;
    }

    //update steps counter
    public boolean updateSteps(String steps, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STEPS_TAKEN, steps);
        db.update("user_table", contentValues, "email=?", new String[]{email});
        return true;
    }

    public boolean updateCalories(String calories, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CALORIES_BURNED, calories);
        db.update("user_table", contentValues, "email=?", new String[]{email});
        return true;
    }

    public boolean updateWorkoutStatus(String workoutStatus, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WORKOUT_STATUS, workoutStatus);
        db.update("user_table", contentValues, "email=?", new String[]{email});
        return true;
    }

    public void deleteAllWorkouts(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from user_workouts where email=?", new String[]{email});
        db.close();
    }

    public boolean updateMealStatus(String mealStatus, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MEAL_STATUS, mealStatus);
        db.update("user_table", contentValues, "email=?", new String[]{email});
        return true;
    }

    //insert new message
    public void insertNewMessage(String email, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7, email);
        contentValues.put(COL_25, message);
        db.insertWithOnConflict(TABLE_NAME_MESSAGES, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    //insert new message
    public void insertNewWorkout(String email, String workout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7, email);
        contentValues.put(COL_26, workout);
        db.insertWithOnConflict(TABLE_NAME_WORKOUTS, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    //get workouts
    public ArrayList<String> getWorkoutsList(String email){
        ArrayList<String> workoutList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_workouts where email=?", new String[]{email});
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(COL_26);
            workoutList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return workoutList;
    }

    //delete message (if needed)
    public void deleteMessage (String message){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_MESSAGES, COL_25 + " - ", new String[]{message});
        db.close();
    }

    //get list of messages
    public ArrayList<String> getMessagesList(String email){
        ArrayList<String> messageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_message where email=?", new String[]{email});
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(COL_25);
            messageList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return messageList;
    }

    //get list of trainees
    public ArrayList<String> getTraineeList(String trainer){
        ArrayList<String> traineeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where trainer=?", new String[]{trainer});
        while(cursor.moveToNext()){
            int indexName = cursor.getColumnIndex(COL_1);
            int indexSurname = cursor.getColumnIndex(COL_2);
            traineeList.add(cursor.getString(indexName) + " " + cursor.getString(indexSurname));
        }
        cursor.close();
        db.close();
        return traineeList;
    }

    public ArrayList<String> getTraineeListEmail(String trainer){
        ArrayList<String> traineeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where trainer=?", new String[]{trainer});
        while(cursor.moveToNext()){
            int indexEmail = cursor.getColumnIndex(COL_7);
            traineeList.add(cursor.getString(indexEmail));
        }
        cursor.close();
        db.close();
        return traineeList;
    }

    //List of all workouts JSON file
    public static ArrayList<WorkoutType> loadWorkout(Context context){
        ArrayList<WorkoutType> workouts = new ArrayList<>();
        String json = "";

        try {
            InputStream is = context.getAssets().open("girlsBench.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("girlsBenchmark");

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                WorkoutType workout = new WorkoutType();
                workout.setTitle(jsonObject.getString("title"));
                workout.setWod(jsonObject.getString("wod"));

                workouts.add(workout);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return workouts;
    }

}
