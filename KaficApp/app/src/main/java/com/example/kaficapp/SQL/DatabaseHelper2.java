package com.example.kaficapp.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kaficapp.Pica;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pica_db";
    private static final String TABLE_NAME = "pica";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAZIV = "naziv";
    private static final String COLUMN_CENA = "cena";

    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " integer PRIMARY KEY," + COLUMN_NAZIV +
                " TEXT," + COLUMN_CENA + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addPice(Pica pice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAZIV, pice.getNaziv());
        values.put(COLUMN_CENA, pice.getCena());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Pica getPice(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID,COLUMN_NAZIV, COLUMN_CENA }, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        Pica pice = new Pica(cursor.getString(0), cursor.getString(1), cursor.getString(2));

        db.close();
        return pice;
    }

    public List<Pica> getAllPica(){
        List<Pica> listaPica = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Pica pice = new Pica(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                listaPica.add(pice);
            }
            while (cursor.moveToNext());
        }

        db.close();
        return listaPica;
    }

    public int updatePica(Pica pice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAZIV, pice.getNaziv());
        values.put(COLUMN_CENA, pice.getCena());

        return db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[] { String.valueOf(pice.getId()) });
    }

    public void deletePice(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[] {id});
        //String.valueOf(pice.getId())
        db.close();
    }

    public int brojac(){
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

}
