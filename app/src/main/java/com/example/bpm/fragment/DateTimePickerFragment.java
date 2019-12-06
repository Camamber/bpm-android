package com.example.bpm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.appeaser.sublimepickerlibrary.SublimePicker;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeListenerAdapter;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;

class DateTimePickerFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        SublimeListenerAdapter mListener = new SublimeListenerAdapter() {

            @Override
            public void onDateTimeRecurrenceSet(SublimePicker sublimeMaterialPicker, SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {

//                recurrenceRule?.let{
//                    // Do something with recurrenceRule
//                }
//
//                recurrenceOption?.let {
//                    // Do something with recurrenceOption
//                    // Call to recurrenceOption.toString() to get recurrenceOption as a String
//                }
            }

            @Override
            public void onCancelled() {

            }

        };

        SublimePicker sublimePicker = new SublimePicker(getContext());
        SublimeOptions sublimeOptions = new SublimeOptions(); // This is optional
        sublimeOptions.setPickerToShow(SublimeOptions.Picker.REPEAT_OPTION_PICKER); // I want the recurrence picker to show.
        sublimeOptions.setDisplayOptions(SublimeOptions.ACTIVATE_RECURRENCE_PICKER); // I only want the recurrence picker, not the date/time pickers.
        sublimePicker.initializePicker(sublimeOptions,mListener);
        return sublimePicker;
    }
    }