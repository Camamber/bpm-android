package com.example.bpm;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.bpm.dao.MeasurementDao;
import com.example.bpm.database.AppDatabase;
import com.example.bpm.entity.Measurement;
import com.example.bpm.entity.MeasurementEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MeasurementTest {
    private MeasurementDao measurementDao;
    private AppDatabase db;

    /** Create fake db in memory before test starts
     * @author Yehor Kaliuzhniy
     */
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        measurementDao = db.measurementDao();
    }

    /** Delete fake db and clear memory after all
     * @author Yehor Kaliuzhniy
     */
    @After
    public void closeDb() throws IOException {
        db.close();
    }

    /** Test the insertion and fetching by id
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void insertAndSelectTest() throws Exception {
        Measurement measurement = new Measurement(new Date(), 120, 60, 75, 'a', 'g', 's');
        long id = measurementDao.insert(measurement);

        Measurement byId = measurementDao.find(id);
        assertEquals(byId.measuredAt, measurement.measuredAt);
    }

    /** Test slect for period functionality
     * fill temporary db with fake measurements and then select range
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void selectForPeriodTest() throws Exception {
        Calendar c = Calendar.getInstance();
        Date now = new Date();


        for (int i = 0; i < 10; i++) {
            c.setTime(now);
            c.add(Calendar.DATE, i);
            Measurement measurement = new Measurement(c.getTime(), 120, 60, 75, 'a', 'g', 's');
            measurementDao.insert(measurement);
        }

        c.setTime(now);
        c.add(Calendar.DATE, 5);

        List<MeasurementEvent> forPeriod = measurementDao.findForPeriodTest(now, c.getTime());
        assertEquals(forPeriod.size(), 5);

    }

    /** Test the update
     * crete measurement then fetch it and change param
     * update and again fetch it to check if it updated
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void updateTest() throws Exception {
        Measurement measurement = new Measurement(new Date(), 120, 60, 75, 'a', 'g', 's');
        long id = measurementDao.insert(measurement);

        measurement = measurementDao.find(id);
        measurement.pulse = 85;
        measurementDao.update(measurement);

        Measurement updated = measurementDao.find(id);
        assertEquals(updated.pulse, 85);
    }

    /** Test delete function
     * Create record then fetch it, delet and again fetch to get null
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void deleteTest() throws Exception {
        Measurement measurement = new Measurement(new Date(), 120, 60, 75, 'a', 'g', 's');
        long id = measurementDao.insert(measurement);

        measurement = measurementDao.find(id);
        measurementDao.delete(measurement);

        Measurement deleted = measurementDao.find(id);
        assertNull(deleted);
    }
}