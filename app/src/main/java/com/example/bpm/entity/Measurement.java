package com.example.bpm.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.bpm.convertor.DateConverter;

import java.util.Date;

@Entity(tableName = "measurements")
public class Measurement {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "measured_at")
    public Date measuredAt;

    @ColumnInfo(name = "arterial")
    public int arterial;

    @ColumnInfo(name = "venous")
    public int venous;

    @ColumnInfo(name = "pulse")
    public int pulse;

    @ColumnInfo(name = "activity")
    public char activity;

    @ColumnInfo(name = "feeling")
    public char feeling;

    @ColumnInfo(name = "weather")
    public char weather;

    public Measurement(Date measuredAt, int arterial, int venous, int pulse, char activity, char feeling, char weather) {
        this.measuredAt = measuredAt;
        this.arterial = arterial;
        this.venous = venous;
        this.pulse = pulse;
        this.activity = activity;
        this.feeling = feeling;
        this.weather = weather;
    }

}