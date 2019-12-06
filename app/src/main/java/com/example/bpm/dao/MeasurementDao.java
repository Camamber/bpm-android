package com.example.bpm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.bpm.convertor.DateConverter;
import com.example.bpm.entity.Measurement;
import com.example.bpm.entity.MeasurementEvent;

import java.util.Date;
import java.util.List;

@Dao
public interface MeasurementDao {

    @Query("SELECT * FROM measurements")
    List<Measurement> findAll();

    @Query("SELECT * FROM measurements WHERE id = :measurementId")
    Measurement find(long measurementId);

    @Query("SELECT id, measured_at FROM measurements WHERE measured_at >= :startDate AND measured_at < :endDate")
    @TypeConverters({DateConverter.class})
    LiveData<List<MeasurementEvent>> findForPeriod(Date startDate, Date endDate);


    @Insert
    long insert(Measurement measurement);

    @Insert
    void insertAll(Measurement... measurement);


    @Update
    void update(Measurement measurement);

    @Delete
    void delete(Measurement measurement);
}