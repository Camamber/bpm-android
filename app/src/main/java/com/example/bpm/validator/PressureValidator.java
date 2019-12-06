package com.example.bpm.validator;


public class PressureValidator {
    public static int value;
    public static String error;
    public static boolean validate(String input) {
        try {
            value = Integer.parseInt(input);
            if(value > 300) {
                throw new Exception("Value cant be bigger than 300");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            error = e.getMessage();
            return false;
        }
    }
}
