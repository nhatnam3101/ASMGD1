package com.example.asmgd1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmgd1.Database.Dbhelper;
import com.example.asmgd1.Model.Khoahoc;

import java.util.ArrayList;

public class KhoahocDao {

    static SQLiteDatabase db2;
      Dbhelper Helper;

    public KhoahocDao(Context context) {
        Helper = new Dbhelper(context);
        db2 = Helper.getWritableDatabase();
    }
    public void insert(Khoahoc kh) {
        ContentValues values = new ContentValues();
        values.put("MaKH", kh.Makh);
        values.put("Tenkh", kh.Tenkh);
        values.put("startt", kh.startt);
        values.put("endt", kh.endt);
        db2.insert("KHOAHOC", null, values);
    }
    public  void update(Khoahoc kh){
        ContentValues values = new ContentValues();
        values.put("MaKH", kh.Makh);
        values.put("Tenkh", kh.Tenkh);
        values.put("startt", kh.startt);
        values.put("endt", kh.endt);

        db2.update("KHOAHOC",values, "MaKH=?", new String[]{kh.Makh});
    }
    public void delete(String Makh) {
        db2.delete("KHOAHOC", "MaKH=?", new String[]{Makh});
    }

    public static ArrayList<Khoahoc> getAllKhoaHoc(Context context) {
        ArrayList<Khoahoc> list = new ArrayList<>();
        Dbhelper helper = new Dbhelper(context);
        SQLiteDatabase db2 = helper.getReadableDatabase();
        String sql = "SELECT * FROM KHOAHOC";
        Cursor cs = db2.rawQuery(sql, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false) {
            String Makh = cs.getString(0);
            String Tenkh = cs.getString(1);
            String startt = cs.getString(2);
            String endt = cs.getString(3);
            list.add(new Khoahoc(Makh, Tenkh,startt,endt));
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
}
