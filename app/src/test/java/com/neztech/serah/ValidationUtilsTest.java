package com.neztech.serah;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.neztech.serah.utils.ValidationUtils;

public class ValidationUtilsTest {

    @Test
    public void isValidEmail_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(ValidationUtils.isValidEmail("test@example.com"));
    }

    @Test
    public void isValidEmail_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(ValidationUtils.isValidEmail("test@mail.example.com"));
    }

    @Test
    public void isValidEmail_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(ValidationUtils.isValidEmail("test@"));
    }

    @Test
    public void isValidEmail_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(ValidationUtils.isValidEmail("test@example..com"));
    }

    @Test
    public void isValidEmail_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(ValidationUtils.isValidEmail("@example.com"));
    }

    @Test
    public void isValidPhoneNumber_CorrectNumber_ReturnsTrue() {
        assertTrue(ValidationUtils.isValidPhoneNumber("1234567890"));
    }

    @Test
    public void isValidPhoneNumber_IncorrectNumberTooShort_ReturnsFalse() {
        assertFalse(ValidationUtils.isValidPhoneNumber("123456789"));
    }

    @Test
    public void isValidPhoneNumber_IncorrectNumberTooLong_ReturnsFalse() {
        assertFalse(ValidationUtils.isValidPhoneNumber("12345678901"));
    }

    @Test
    public void isValidPhoneNumber_IncorrectNumberContainsLetter_ReturnsFalse() {
        assertFalse(ValidationUtils.isValidPhoneNumber("123456789a"));
    }
}
