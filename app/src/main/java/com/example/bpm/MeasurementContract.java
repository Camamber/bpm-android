package com.example.bpm;

import android.provider.BaseColumns;

public final class MeasurementContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MeasurementContract() {}

    /* Inner class that defines the table contents */
    public static class MeasurementEntry implements BaseColumns {
        public static final String TABLE_NAME = "measurements";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_ARTERIAL = "arterial";
        public static final String COLUMN_NAME_VENOUS = "venous";
        public static final String COLUMN_NAME_PULSE = "pulse";
        public static final String COLUMN_NAME_ACTIVITY = "activity";
        public static final String COLUMN_NAME_FEELING = "feeling";
        public static final String COLUMN_NAME_WEATHER = "weather";
    }
}