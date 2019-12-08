package com.example.bpm.convertor;

import androidx.room.TypeConverter;

import java.util.Date;

/** Represent special Room converter
 * convert Data to timestamp and vice-versa
 * @author Yehor Kaliuzhniy
 */
public class DateConverter {

    /** Convert timestamp to Date
     * @author Yehor Kaliuzhniy
     * @param value timestamp provided
     * @return Date result of convertion
     */
    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    /** Convert Date to timestamp
     * @author Yehor Kaliuzhniy
     * @param date Date object provided
     * @return Long result of convertion
     */
    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}
