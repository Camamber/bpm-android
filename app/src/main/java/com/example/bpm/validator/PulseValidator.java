package com.example.bpm.validator;


public class PulseValidator {
    public static int value;
    public static String error;
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
