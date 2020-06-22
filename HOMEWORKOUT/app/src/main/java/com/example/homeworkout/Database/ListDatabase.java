package com.example.homeworkout.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.Nullable;

import java.net.HttpURLConnection;


public class ListDatabase extends SQLiteOpenHelper {

    public ListDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public  void INSERT_TD(String ten, String mota, byte[] anh) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO TheDuc VALUES(null, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, ten);
        statement.bindString(2, mota);
        statement.bindBlob(3, anh);

        statement.executeInsert();
    }
    public  void INSERT_TDBai(int id_mon,String tenbai, String HuongDan, byte[] anh) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO TheDucBai VALUES(null, ?, ?, ?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindLong(1, id_mon);
        statement.bindString(2, tenbai);
        statement.bindString(3, HuongDan);
        statement.bindBlob(4, anh);
        statement.executeInsert();
    }

    public void INSERT_LICH(int weight, int height, double bmi) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Lich VALUES(null, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindLong(1, weight);
        statement.bindLong(2, height);
        statement.bindDouble(3, bmi);

        statement.executeInsert();
    }

    public Cursor getData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
