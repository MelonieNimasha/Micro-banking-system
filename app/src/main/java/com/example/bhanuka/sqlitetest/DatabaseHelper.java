package com.example.bhanuka.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mobileDB.db";
    public static final String TABLE_NAME = "transactions";
    public static final String COL_1 = "transaction_id";
    public static final String COL_2 = "account_number";
    public static final String COL_3 = "type";
    public static final String COL_4 = "date";
    public static final String COL_5 = "time";
    public static final String COL_6 = "amount";
    public static final String COL_7 = "details";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        System.out.println("database created :" + DATABASE_NAME);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT, account_number INTEGER, type TEXT, date TEXT, time TEXT, amount INTEGER, details TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_NAME );
        onCreate(db);
    }

    public boolean insertData(String account_number, String type, String date, String time, String amount, String details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, account_number);
        contentValues.put(COL_3, type);
        contentValues.put(COL_4, date);
        contentValues.put(COL_5, time);
        contentValues.put(COL_6, amount);
        contentValues.put(COL_7, details);
        long insert = db.insert(TABLE_NAME, null, contentValues);
        if (insert == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_NAME;
        Cursor mCursor = db.rawQuery(q, null);
        return mCursor;
    }
}
