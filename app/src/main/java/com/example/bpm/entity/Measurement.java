package com.example.bpm.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.bpm.convertor.DateConverter;

import java.util.Date;

/** Represent a measurement model
 * @author Yehor Kaliuzhniy
 */
@Entity(tableName = "measurements")
public class Measurement {

    /** Represent a column with id value */
    @PrimaryKey(autoGenerate = true)
    public long id;

    /** Represent a column with a date when it was measured value */
    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "measured_at")
    public Date measuredAt;

    /** Represent a column with arterial pressure value */
    @ColumnInfo(name = "arterial")
    public int arterial;

    /** Represent a column with venous pressure value */
    @ColumnInfo(name = "venous")
    public int venous;

    /** Represent a column with pulse value */
    @ColumnInfo(name = "pulse")
    public int pulse;

    @ColumnInfo(name = "activity")
    public char activity;

    @ColumnInfo(name = "feeling")
    public char feeling;

    @ColumnInfo(name = "weather")
    public char weather;

    /** Constructor of measurement
     * @author Yehor Kaliuzhniy
     * @param measuredAt date when measurement was measured
     * @param arterial value of arterial pressure
     * @param venous value of venous oressure
     * @param  pulse value of pulse
     */
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