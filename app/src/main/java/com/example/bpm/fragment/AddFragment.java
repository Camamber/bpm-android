package com.example.bpm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.example.bpm.R;
import com.example.bpm.dao.MeasurementDao;
import com.example.bpm.database.AppDatabase;
import com.example.bpm.entity.Measurement;
import com.example.bpm.validator.DateTimeValidator;
import com.example.bpm.validator.PressureValidator;
import com.example.bpm.validator.PulseValidator;

/** Represents an Measurement Ad fragment and handle View Events.
 * @author Yehor Kaliuzhniy
 */
public class AddFragment extends Fragment {

    /** Represent all user interfaces interactive elements */
    Button btnAdd;
    int arterial, venous, pulse;
    EditText etMeasuredAt, etArterial, etVenous, etPulse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        etMeasuredAt = (EditText) view.findViewById(R.id.et_date);
        etMeasuredAt.setText(getCurrentDate());

        etArterial = (EditText) view.findViewById(R.id.et_arterial);
        etVenous = (EditText) view.findViewById(R.id.et_venous);
        etPulse = (EditText) view.findViewById(R.id.et_pulse);

        btnAdd = (Button) view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(onBtnAdd);

        return view;
    }

    /** On buttin add handler */
    private View.OnClickListener onBtnAdd = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!validate()) {
                return;
            }

            AppDatabase db = AppDatabase.getAppDatabase(getContext());
            MeasurementDao measurementDao = db.measurementDao();

            Measurement measurement = new Measurement(DateTimeValidator.date, arterial, venous, pulse, 'a', 'g', 's');
            measurementDao.insert(measurement);
            Toast toast = Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT);
            toast.show();
            clearFields();
        }
    };


    /** Clear old user input
     * @author Yehor Kaliuzhniy
     */
    private void clearFields() {
        etMeasuredAt.setText(getCurrentDate());
        etArterial.setText("");
        etVenous.setText("");
        etPulse.setText("");
    }

    /** validate all user input
     * @author Yehor Kaliuzhniy
     * @return boolean
     * the result of validation is true if valid
     */
    private boolean validate() {
        if (!DateTimeValidator.validate(etMeasuredAt.getText().toString())) {
            Toast toast = Toast.makeText(getContext(),
                    DateTimeValidator.error, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        if (!PressureValidator.validate(etArterial.getText().toString())) {
            Toast toast = Toast.makeText(getContext(),
                    PulseValidator.error, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        arterial = PressureValidator.value;

        if (!PressureValidator.validate(etVenous.getText().toString())) {
            Toast toast = Toast.makeText(getContext(),
                    PressureValidator.error, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        venous = PressureValidator.value;

        if (!PulseValidator.validate(etPulse.getText().toString())) {
            Toast toast = Toast.makeText(getContext(),
                    PulseValidator.error, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        pulse = PulseValidator.value;

        return true;
    }

    /** get current formatted date
     * @author Yehor Kaliuzhniy
     * @return String formatted date
     */
    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getDefault());
        Date today = new Date();
        return dateFormat.format(today);
    }
}
