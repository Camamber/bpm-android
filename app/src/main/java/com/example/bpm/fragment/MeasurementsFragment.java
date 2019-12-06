package com.example.bpm.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.bpm.R;
import com.example.bpm.activity.MeasurementActivity;
import com.example.bpm.dao.MeasurementDao;
import com.example.bpm.database.AppDatabase;
import com.example.bpm.entity.Measurement;
import com.example.bpm.entity.MeasurementEvent;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MeasurementsFragment extends Fragment {

    CompactCalendarView compactCalendarView;
    MeasurementDao measurementDao;
    LiveData<List<MeasurementEvent>> liveDataMeasurementEvents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measurements, container, false);

        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        measurementDao = db.measurementDao();

        compactCalendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        compactCalendarView.setListener(compactCalendarViewListener);

        liveDataMeasurementEvents = initLiveData();
        observeLiveData();

        return view;
    }

    private CompactCalendarView.CompactCalendarViewListener compactCalendarViewListener = new CompactCalendarView.CompactCalendarViewListener() {
        @Override
        public void onDayClick(Date dateClicked) {
            List<Event> events = compactCalendarView.getEvents(dateClicked);
            Intent intent = new Intent(getContext(), MeasurementActivity.class);

            if (events.size() > 0) {
                Bundle b = new Bundle();
                b.putLong("id", (Long) (events.get(0).getData())); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        }

        @Override
        public void onMonthScroll(Date firstDayOfNewMonth) {
            Calendar c = Calendar.getInstance();
            c.setTime(firstDayOfNewMonth);
            c.add(Calendar.MONTH, 1);
            liveDataMeasurementEvents = measurementDao.findForPeriod(firstDayOfNewMonth, c.getTime());
            observeLiveData();
        }
    };

    private LiveData<List<MeasurementEvent>> initLiveData() {
        // get today and clear time of day
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = cal.getTime();

        cal.add(Calendar.MONTH, 1);
        Date endDate = cal.getTime();

        return measurementDao.findForPeriod(startDate, endDate);
    }

    private void observeLiveData() {
        liveDataMeasurementEvents.observe(this, new Observer<List<MeasurementEvent>>() {
            @Override
            public void onChanged(@Nullable List<MeasurementEvent> measurementEvents) {
                compactCalendarView.removeAllEvents();
                if (measurementEvents != null) {
                    for (MeasurementEvent measurementEvent : measurementEvents) {
                        Event event = new Event(Color.GREEN, measurementEvent.measuredAt.getTime(), measurementEvent.id);
                        compactCalendarView.addEvent(event);
                    }
                }
            }
        });
    }
}
