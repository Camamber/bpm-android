package com.example.bpm.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeValidator {
    public static Date date;
    public static String error;
    public static boolean validate(String input) {
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(input);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            error = e.getMessage();
            return false;
        }
    }
}
