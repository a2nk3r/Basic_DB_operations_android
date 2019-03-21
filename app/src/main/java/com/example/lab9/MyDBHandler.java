package com.example.lab9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDB.db";
    private static final String TABLE_NAME = "Student";
    private static final String COLUMN_ROLL = "RollNumber";
    private static final String COLUMN_NAME = "StudentName";
    private static final String COLUMN_AGE = "StudentAge";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + COLUMN_ROLL + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " + COLUMN_AGE + " INTEGER )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String loadHandler(){
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            int r_0 = cursor.getInt(0);
            String r_1 = cursor.getString(1);
            int r_2 = cursor.getInt(2);
            result += String.valueOf(r_0) + " " + r_1 + " " + String.valueOf(r_2) + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(Student student){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROLL, student.getRollNumber());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public Student findHandler(String studentName){
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + studentName + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if(cursor.moveToFirst()){
            student.setRollNumber(Integer.parseInt(cursor.getString(0)));
            student.setName(cursor.getString(1));
            student.setAge(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        }
        else{
            student = null;
        }
        db.close();
        return student;
    }
    public boolean deleteHandler(int roll){
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ROLL + " = '" + String.valueOf(roll) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if(cursor.moveToFirst()){
            student.setRollNumber(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ROLL + "=?", new String[]{String.valueOf(student.getRollNumber())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(int roll, String name, int age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ROLL, roll);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_AGE, age);
        return db.update(TABLE_NAME, args, COLUMN_ROLL + "=" + roll, null) > 0;
    }
}
