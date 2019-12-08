package com.example.bpm.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Represent a DateTime validator and convertor
 * @author Yehor Kaliuzhniy
 */
public class DateTimeValidator {
    /** validated and converted value */
    public static Date date;

    /** Error message if error was rised during validation */
    public static String error;

    /** Validate method
     * @author Yehor Kaliuzhniy
     * @param input string that should be validated
     * @return is valid string
     */
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
