package com.example.bpm;

import com.example.bpm.validator.DateTimeValidator;
import com.example.bpm.validator.PressureValidator;
import com.example.bpm.validator.PulseValidator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Represent a Tests for validator classes
 * @author Yehor Kaliuzhniy
 */
public class ValidatorTest {


    /** Test if datetime is valid provide valid data
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void DateTimeValidatorTest() {
        boolean result = DateTimeValidator.validate("2019-12-03 22:10");
        assertEquals(true, result);
    }

    /** Test if datetime is valid provide invalid data
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void DateTimeValidatorFailureTest() {
        boolean result = DateTimeValidator.validate("Wrong dateTime");
        assertEquals(false, result);
    }


    /** Test if pressure is valid provide valid data
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void PressureValidatorTest() {
        boolean result = PressureValidator.validate("120");
        assertEquals(true, result);
    }

    /** Test if pressure is valid provide invalid data
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void PressureValidatorFailureTest() {
        boolean result = PressureValidator.validate("Wrong value");
        assertEquals(false, result);
    }

    /** Test if pressure is valid provide out of rules data
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void PressureValidatorFailureRangeTest() {
        boolean result = PressureValidator.validate("500");
        assertEquals(false, result);
    }


    /** Test if pulse is valid provide valid data
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void PulseValidatorTest() {
        boolean result = PulseValidator.validate("75");
        assertEquals(true, result);
    }

    /** Test if pulse is valid provide invalid data
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void PulseValidatorFailureTest() {
        boolean result = PulseValidator.validate("Wrong pulse");
        assertEquals(false, result);
    }

    /** Test if pulse is valid provide out of rules data
     * @author Yehor Kaliuzhniy
     */
    @Test
    public void PulseValidatorFailureRangeTest() {
        boolean result = PressureValidator.validate("1000");
        assertEquals(false, result);
    }
}
