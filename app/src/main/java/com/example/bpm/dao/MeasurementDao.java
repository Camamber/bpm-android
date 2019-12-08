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
/** Represent DAO object
 * describe sql query to db table of measurements
 * @author Yehor Kaliuzhniy
 */
@Dao
public interface MeasurementDao {

    /** Fetch all measuremnts in table
     * @author Yehor Kaliuzhniy
     * @return List of measuremnts
     */
    @Query("SELECT * FROM measurements")
    List<Measurement> findAll();

    /** Fetch  measuremnt by id
     * @author Yehor Kaliuzhniy
     * @param measurementId id of needed measurment
     * @return Measurement object or null
     */
    @Query("SELECT * FROM measurements WHERE id = :measurementId")
    Measurement find(long measurementId);


    @Query("SELECT id, measured_at FROM measurements WHERE measured_at >= :startDate AND measured_at < :endDate")
    @TypeConverters({DateConverter.class})
    LiveData<List<MeasurementEvent>> findForPeriod(Date startDate, Date endDate);


    /** Fetch  measuremnts in period
     * @author Yehor Kaliuzhniy
     * @param startDate start date of period
     * @param endDate end date of period
     * @return List of Measurement objects
     */
    @Query("SELECT id, measured_at FROM measurements WHERE measured_at >= :startDate AND measured_at < :endDate")
    @TypeConverters({DateConverter.class})
    List<MeasurementEvent> findForPeriodTest(Date startDate, Date endDate);


    /** Insert measuremnts in table
     * @author Yehor Kaliuzhniy
     * @param measurement
     * @return id of inserted measuremnet
     */
    @Insert
    long insert(Measurement measurement);

    /** Multimple insert measuremnts in table
     * @author Yehor Kaliuzhniy
     * @param measurements
     */
    @Insert
    void insertAll(Measurement... measurements);


    /** Update existing record in table
     * @author Yehor Kaliuzhniy
     * @param measurement
     */
    @Update
    void update(Measurement measurement);

    /** Delete existing record in table
     * @author Yehor Kaliuzhniy
     * @param measurement
     */
    @Delete
    void delete(Measurement measurement);
}