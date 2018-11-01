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

    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String COL_8 = "customer_NIC";
    public static final String COL_9 = "account_type";
    public static final String COL_10 = "status";
    public static final String COL_11 = "current_balance";
    public static final String COL_12 = "account_details";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        System.out.println("database created :" + DATABASE_NAME);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT, account_number INTEGER REFERENCES accounts(account_number), type TEXT, date TEXT, time TEXT, amount INTEGER, details TEXT)");
        db.execSQL("create table " + TABLE_ACCOUNTS + "(account_number INTEGER PRIMARY KEY, customer_NIC TEXT, account_type TEXT, status TEXT, current_balance INTEGER, account_details TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_NAME );
        db.execSQL("drop table if exists " + TABLE_ACCOUNTS );
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

    public boolean insertToAccounts(String account_number, String customer_NIC, String account_type, String status, String current_balance, String account_details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, account_number);
        contentValues.put(COL_8, customer_NIC);
        contentValues.put(COL_9, account_type);
        contentValues.put(COL_10, status);
        contentValues.put(COL_11, current_balance);
        contentValues.put(COL_12, account_details);
        long insert = db.insert(TABLE_ACCOUNTS, null, contentValues);
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

    public Cursor getAccounts() {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_ACCOUNTS;
        Cursor mCursor = db.rawQuery(q, null);
        return mCursor;
    }
}
