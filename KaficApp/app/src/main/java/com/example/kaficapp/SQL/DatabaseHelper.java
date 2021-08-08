package com.example.kaficapp.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kaficapp.User;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," +
            COLUMN_PHONE + " TEXT," + COLUMN_PASS + " TEXT)";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertUser(User user){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_PHONE, user.getPhone());
        values.put(COLUMN_PASS, user.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public String searhPass(String ime) {
        db = this.getReadableDatabase();
        String query = "SELECT name, pass FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "NOT FOUND";

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(ime)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }
}
