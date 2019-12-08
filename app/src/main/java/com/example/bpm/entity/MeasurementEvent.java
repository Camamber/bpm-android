package com.example.bpm.entity;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.bpm.convertor.DateConverter;

import java.util.Date;

/** Represent a measurement event model
 * @author Yehor Kaliuzhniy
 */
public class MeasurementEvent {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "measured_at")
    public Date measuredAt;
}
