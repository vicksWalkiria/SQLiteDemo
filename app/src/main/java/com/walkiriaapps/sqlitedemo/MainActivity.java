package com.walkiriaapps.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.db_results);
        CatDbHelper dbHelper = new CatDbHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //INSERT

        //Create container
//        ContentValues values = new ContentValues();
//        values.put(CatContract.CatEntry.NAME, "Socks");
//        values.put(CatContract.CatEntry.AGE, "6");
//        values.put(CatContract.CatEntry.STATUS, "Adopted");
//
//        //insert values
//        db.insert(CatContract.CatEntry.TABLE_NAME, null, values);


        Log.d("WALKIRIA", " INSERTING INTO DATABASE");


        //READ

        db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                CatContract.CatEntry.NAME,
                CatContract.CatEntry.AGE,
                CatContract.CatEntry.STATUS
        };

        // [OPTIONAL FOR WHERE CLAUSE ]Filter results WHERE "title" = 'My Title'
        String selection = CatContract.CatEntry.NAME + " = ?";
        String[] selectionArgs = {"Socks"};

        // [OPTIONAL] How you want the results sorted in the resulting Cursor
        String sortOrder =
                CatContract.CatEntry.AGE + " DESC";

        Cursor cursor = db.query(
                CatContract.CatEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        //ALTERNATIVE
        Cursor cursor1 = db.rawQuery("SELECT * FROM " + CatContract.CatEntry.TABLE_NAME, null);

        String contents = "";
        String contents1 = "";

        //ITERATE CURSOR
        try {
            while (cursor.moveToNext() && cursor1.moveToNext()) {
                contents += cursor.getString(1) + " - "+ cursor.getInt(2) +"\n";
                contents1 += cursor1.getString(1) + " - " + cursor.getString(3)+"\n";
            }
        } finally {
            cursor.close();
        }

        tv.setText(contents+"\n\nCONTENTS1\n"+contents1);


    }
}