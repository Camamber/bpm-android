package com.example.bpm.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bpm.R;
import com.example.bpm.dao.MeasurementDao;
import com.example.bpm.database.AppDatabase;
import com.example.bpm.entity.Measurement;
import com.example.bpm.entity.MeasurementEvent;
import com.example.bpm.validator.DateTimeValidator;
import com.example.bpm.validator.PressureValidator;
import com.example.bpm.validator.PulseValidator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MeasurementActivity extends AppCompatActivity {

    MeasurementDao measurementDao;
    Measurement measurement;

    EditText etMeasuredAt, etArterial, etVenous, etPulse;
    Button btnSave, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        etMeasuredAt = (EditText) findViewById(R.id.et_date2);
        etArterial = (EditText) findViewById(R.id.et_arterial2);
        etVenous = (EditText) findViewById(R.id.et_venous2);
        etPulse = (EditText) findViewById(R.id.et_pulse2);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(onBtnSave);

        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(onBtnDelete);

        AppDatabase db = AppDatabase.getAppDatabase(this);
        measurementDao = db.measurementDao();

        initMeasurement();
    }

    private void initMeasurement() {
        Bundle b = getIntent().getExtras();
        long id = b.getLong("id");
        measurement = measurementDao.find(id);

        etMeasuredAt.setText(formatDate(measurement.measuredAt));
        etArterial.setText(String.valueOf(measurement.arterial));
        etVenous.setText(String.valueOf(measurement.venous));
        etPulse.setText(String.valueOf(measurement.pulse));
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(date);
    }

    private View.OnClickListener onBtnSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!validate()) {
                return;
            }
            measurementDao.update(measurement);
            Toast toast = Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    private View.OnClickListener onBtnDelete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            measurementDao.delete(measurement);
            Toast toast = Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
    };

    private boolean validate() {
        if (!DateTimeValidator.validate(etMeasuredAt.getText().toString())) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    DateTimeValidator.error, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        if (!PressureValidator.validate(etArterial.getText().toString())) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    PulseValidator.error, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        measurement.arterial = PressureValidator.value;

        if (!PressureValidator.validate(etVenous.getText().toString())) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    PressureValidator.error, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        measurement.venous = PressureValidator.value;

        if (!PulseValidator.validate(etPulse.getText().toString())) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    PulseValidator.error, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        measurement.pulse = PulseValidator.value;

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
