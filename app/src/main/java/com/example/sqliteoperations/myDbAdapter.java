package com.example.sqliteoperations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDbAdapter extends SQLiteOpenHelper {


    public myDbAdapter(Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Register(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if Exists Register");
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        Long result = DB.insert("Register", null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean updateData(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("select * from Register where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            int result = DB.update("Register", contentValues, "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else
                return true;
        } else
            return false;
    }

    public boolean deleteData(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Register where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            int result = DB.delete("Register", "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else
                return true;
        } else
            return false;
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Register", null);
        return cursor;
    }
}
