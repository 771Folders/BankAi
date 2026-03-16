package com.example.bankai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "XeanBank.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Accounts(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT," +
                "Password TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Accounts;");
        onCreate(sqLiteDatabase);
    }

    public Boolean login(String Username, String Password) {
        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Accounts WHERE Username = ? AND Password = ?", new String[]{Username, Password});

        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public void register(String Username, String Password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Username", Username);
        values.put("Password", Password);

        db.insert("Accounts", null, values);
    }
}