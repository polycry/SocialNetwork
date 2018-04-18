package com.feg.socialnetwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.sql.DataSource;

/**
 * Created by Jonas on 18.04.2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "wetterstation.db";
    public final static int DB_VERSION = 1;

    public final static String TABLE_NAME = "benutzer";
    public final static String COL_ID = "id";
    public final static String COL_NAME_benutzername = "benutzername";
    public final static String COL_NAME_passwort = "passwort";
    public final static String[] COLS = {COL_ID, COL_NAME_benutzername, COL_NAME_passwort};
    private SQLiteDatabase database;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " TEXT NOT NULL, "
                + COL_NAME_benutzername + " TEXT NOT NULL, "
                + COL_NAME_passwort + " TEXT NOT NULL)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void insertUser(String name, String passwort) {
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, 1);
        cv.put(COL_NAME_benutzername, name);
        cv.put(COL_NAME_passwort, passwort);
        database.insert(TABLE_NAME, null, cv);

    }

    public boolean hasStoredCredentials() {
        Cursor c = database.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
        return c.getCount() > 0;


    }

    public String[] getCredentials() {
        String[] credentials = new String[2];
        String sql = "SELECT "+COL_NAME_benutzername+","+COL_NAME_passwort+" FROM "+TABLE_NAME;
        Cursor c = database.rawQuery(sql, null);
        c.moveToFirst();
        credentials[0] = c.getString(0);
        credentials[1] = c.getString(1);

        return credentials;
    }
}
