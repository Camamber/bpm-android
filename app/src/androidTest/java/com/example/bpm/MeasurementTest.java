package com.example.bpm;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        measurementDao = db.measurementDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertAndSelectTest() throws Exception {
        Measurement measurement = new Measurement(new Date(), 120, 60, 75, 'a', 'g', 's');
        long id = measurementDao.insert(measurement);

        Measurement byId = measurementDao.find(id);
        assertEquals(byId.measuredAt, measurement.measuredAt);
    }

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

        LiveData<List<MeasurementEvent>> forPeriod = measurementDao.findForPeriod(now, c.getTime());
        assertEquals(forPeriod.getValue().size(), 5);

    }

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