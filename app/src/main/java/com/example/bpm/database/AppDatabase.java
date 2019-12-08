package com.example.bpm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bpm.dao.MeasurementDao;
import com.example.bpm.entity.Measurement;

/** Represent SINGLETON object of RoomDatabase
 * represent a connection to db
 * @author Yehor Kaliuzhniy
 */
@Database(entities = {Measurement.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract MeasurementDao measurementDao();

    /** Create or return existing instance of connection to database
     * @author Yehor Kaliuzhniy
     * @param context
     * @return appDatabase
     */
    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "bpm")
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    /** Destroy instance and close connection
     * @author Yehor Kaliuzhniy
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }
}