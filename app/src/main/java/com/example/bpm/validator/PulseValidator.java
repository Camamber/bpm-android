package com.example.bpm.validator;


/** Represent a Pulse rules, validator and convertor
 * @author Yehor Kaliuzhniy
 */
public class PulseValidator {
    /** validated and converted value */
    public static int value;

    /** Error message if error was rised during validation */
    public static String error;

    /** Validate method
     * @author Yehor Kaliuzhniy
     * @param input string that should be validated
     * @return is valid string
     */
    public static boolean validate(String input) {
        try {
            value = Integer.parseInt(input);
            if(value > 200) {
                throw new Exception("Value cant be bigger than 200");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            error = e.getMessage();
            return false;
        }
    }
}
