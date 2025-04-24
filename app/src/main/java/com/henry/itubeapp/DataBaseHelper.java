package com.henry.itubeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    Context context;
    private static String dbName = "Manager";
    private static String dbTable = "users";
    private static int dbVersion = 4;
    private static String ID = "id";
    private static String FULL_NAME = "fullname";
    private static String USER_NAME = "username";
    private static String PASSWORD = "password";

    public static String TASK_TABLE = "urls";
    public static String URL = "url";
    public DataBaseHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
            " CREATE TABLE " + dbTable + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FULL_NAME + " TEXT, " +
            USER_NAME + " TEXT UNIQUE, " +
            PASSWORD + " TEXT);";

        String urlQuery =
            "CREATE TABLE " + TASK_TABLE + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            URL + " TEXT);";

        db.execSQL(query);
        db.execSQL(urlQuery);


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + dbTable);
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        onCreate(db);

    }

    public boolean addUser(String fullname, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FULL_NAME, fullname);
        values.put(USER_NAME, username);
        values.put(PASSWORD, password);

        long result = db.insert(dbTable, null, values);
        return result != -1;
    }

    public void addUrl(String url){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(URL, url);

        long result = db.insert(TASK_TABLE, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dbTable + " WHERE " + USER_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbTable + " WHERE " + USER_NAME + " = ? AND " + PASSWORD + " = ?", new String[]{username, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }

    public ArrayList<String> getAllUrls() {
        ArrayList<String> urlList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TASK_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor.getColumnIndexOrThrow(URL));
                urlList.add(url);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return urlList;
    }
}
