package com.example.bpm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bpm.MeasurementContract.MeasurementEntry;


//return db.update(tableName, cv, "id=?s", new String[]{String.valueOf(id)});
public class MeasurementDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BPM.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MeasurementEntry.TABLE_NAME + " (" +
                    MeasurementEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MeasurementEntry.COLUMN_NAME_CREATED_AT + " DATETIME," +
                    MeasurementEntry.COLUMN_NAME_ARTERIAL + " INTEGER," +
                    MeasurementEntry.COLUMN_NAME_VENOUS + " INTEGER," +
                    MeasurementEntry.COLUMN_NAME_PULSE + " INTEGER," +
                    MeasurementEntry.COLUMN_NAME_ACTIVITY + " CHAR(1)," +
                    MeasurementEntry.COLUMN_NAME_FEELING + " CHAR(1)," +
                    MeasurementEntry.COLUMN_NAME_WEATHER + " CHAR(1))";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MeasurementEntry.TABLE_NAME;


    public MeasurementDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
