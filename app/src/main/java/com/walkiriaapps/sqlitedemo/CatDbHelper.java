package com.walkiriaapps.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class CatDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CatContract.CatEntry.TABLE_NAME + " (" +
                    CatContract.CatEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CatContract.CatEntry.NAME + " TEXT NOT NULL,"+
                    CatContract.CatEntry.STATUS + " TEXT NOT NULL,"+
                    CatContract.CatEntry.AGE + " INTEGER NOT NULL)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CatContract.CatEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Cat.db";
    public CatDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //if it's the first time
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("WALKIRIA", SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
