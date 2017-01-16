package com.example.haziny.accountbook;

/**
 * Created by Haziny on 2017-01-10.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        //createTable();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format( "DROP TABLE IF EXISTS %s IncomeExpense" );
        db.execSQL( query );
        onCreate( db );
    }

    public void createTable() {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("CREATE TABLE IncomeExpense( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price INTEGER, iconindex INTEGER, classification INTEGER);");
    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from IncomeExpense", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : name "
                    + cursor.getString(1)
                    + ", price = "
                    + cursor.getInt(2)
                    + "\n";
        }

        return str;
    }
}