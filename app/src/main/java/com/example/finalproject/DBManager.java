package com.example.finalproject;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.util.ArrayList;
import android.content.Context;

public class DBManager {
    private Context context;
    private String DB_NAME="maps.db";

    private SQLiteDatabase db;

    private static DBManager dbManager;

    public static DBManager getInstance(Context context){
        if (dbManager == null){
            dbManager = new DBManager(context);
        }
        return dbManager;
    }

    private void createTablesIfNeedBe() {
        db.execSQL("CREATE TABLE IF NOT EXISTS MARKERS (POSX TEXT, POSY TEXT);");
    }

    private DBManager(Context context) {
        this.context = context;
        db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        createTablesIfNeedBe();
    }

    ArrayList<Result> getAllMarkerData() {
        ArrayList<Result> data = new ArrayList<Result>();
        Cursor cursor = db.rawQuery("SELECT * FROM MARKERS", null);   // ORDER BY score DESC
        boolean hasMoreData = cursor.moveToFirst();

        while (hasMoreData) {
            @SuppressLint("Range") String PosX = cursor.getString(cursor.getColumnIndex("POSX"));
            @SuppressLint("Range") String PosY = cursor.getString(cursor.getColumnIndex("POSY"));
            data.add(new Result(PosX, PosY));
            hasMoreData = cursor.moveToNext();
        }

        return data;
    }

    void addMarkerToDb(String posX, String posY){
        db.execSQL("INSERT INTO MARKERS VALUES ('" + posX + "', " + posY + ");");
    }

    void deleteMarkerFromDb(String posX, String posY){
        db.execSQL("DELETE FROM MARKERS WHERE POSX = '" + posX + "' and POSY = '" + posY + "' ");
    }

}
