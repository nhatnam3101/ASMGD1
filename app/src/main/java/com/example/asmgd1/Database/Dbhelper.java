package com.example.asmgd1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    static final String DbName = "QLKH";
    static final int Version = 1;
    public Dbhelper(Context context){
        super(context,DbName,null,Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlLkh ="CREATE TABLE KHOAHOC(MaKH integer primary key autoincrement," +
                "Tenkh text ,startt date, endt date)";
        db.execSQL(sqlLkh);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlsv = "DROP TABLE Sv ;";
        db.execSQL(sqlsv);

    }
}
