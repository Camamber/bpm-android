package com.example.bpm;

import com.example.bpm.validator.DateTimeValidator;
import com.example.bpm.validator.PressureValidator;
import com.example.bpm.validator.PulseValidator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidatorTest {

    @Test
    public void DateTimeValidatorTest() {
        boolean result = DateTimeValidator.validate("2019-12-03 22:10");
        assertEquals(true, result);
    }

    @Test
    public void DateTimeValidatorFailureTest() {
        boolean result = DateTimeValidator.validate("Wrong dateTime");
        assertEquals(false, result);
    }


    @Test
    public void PressureValidatorTest() {
        boolean result = PressureValidator.validate("120");
        assertEquals(true, result);
    }

    @Test
    public void PressureValidatorFailureTest() {
        boolean result = PressureValidator.validate("Wrong value");
        assertEquals(false, result);
    }

    @Test
    public void PressureValidatorFailureRangeTest() {
        boolean result = PressureValidator.validate("500");
        assertEquals(false, result);
    }

    @Test

    public void PulseValidatorTest() {
        boolean result = PulseValidator.validate("75");
        assertEquals(true, result);
    }

    @Test
    public void PulseValidatorFailureTest() {
        boolean result = PulseValidator.validate("Wrong pulse");
        assertEquals(false, result);
    }

    @Test
    public void PulseValidatorFailureRangeTest() {
        boolean result = PressureValidator.validate("1000");
        assertEquals(false, result);
    }
}
