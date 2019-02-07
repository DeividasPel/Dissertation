package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user2.db";
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

    //WEIGHT HISTORY DB
    public static final String TABLE_NAME_WEIGHT_HISTORY = "weight_history";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, DOB TEXT, HEIGHT TEXT, GENDER TEXT, WEIGHT TEXT, EMAIL TEXT, PASSWORD TEXT, GOAL TEXT, TRAINING TEXT, NOTES TEXT, TRAINER TEXT) ");
        db.execSQL("Create table " + TABLE_NAME_MEAL_PLAN + "(EMAIL TEXT, BREAKFAST1 TEXT, BREAKFAST2 TEXT, BREAKFAST3 TEXT, BREAKFAST_NOTES TEXT, LUNCH1 TEXT, LUNCH2 TEXT, LUNCH3 TEXT, LUNCH_NOTES TEXT, DINNER1 TEXT, DINNER2 TEXT, DINNER3 TEXT, DINNER_NOTES TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEAL_PLAN);
        onCreate(db);
    }

    //inserting to DB
    public boolean insert (String name, String surname, String dob, String height, String gender, String weight, String email, String password, String goal, String training, String notes, String trainer, String breakfast1, String breakfast2, String breakfast3, String breakfast_notes, String lunch1, String lunch2, String lunch3, String lunch_notes, String dinner1, String dinner2, String dinner3, String dinner_notes){
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

    //update records (for trainee)
    public boolean updateRecordsTrainee(String weight, String goal, String training, String notes, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6, weight);
        contentValues.put(COL_9, goal);
        contentValues.put(COL_10, training);
        contentValues.put(COL_11, notes);
        contentValues. put(COL_7, email);
        db.update("user_table", contentValues, "email=?", new String[]{email});
        return true;
    }

}
