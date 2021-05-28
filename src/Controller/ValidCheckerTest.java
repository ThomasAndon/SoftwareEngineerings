package Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidCheckerTest {

    @org.junit.jupiter.api.Test
    void isInvalidID() {
        assertFalse(new ValidChecker().isInvalidID("1234"));
        assertFalse(new ValidChecker().isInvalidID("qwer"));
        assertFalse(new ValidChecker().isInvalidID("12qw"));
        assertFalse(new ValidChecker().isInvalidID("1258ffege"));
        assertTrue(new ValidChecker().isInvalidID("123"));
        assertTrue(new ValidChecker().isInvalidID("123@"));
        assertTrue(new ValidChecker().isInvalidID("12345678w!"));
        assertTrue(new ValidChecker().isInvalidID("qwertyuiop123456"));
        assertTrue(new ValidChecker().isInvalidID("qwer^"));

    }

    @org.junit.jupiter.api.Test
    void isInvalidPw() {
        assertFalse(new ValidChecker().isInvalidPw("1234"));
        assertFalse(new ValidChecker().isInvalidPw("qwer"));
        assertFalse(new ValidChecker().isInvalidPw("12qw"));
        assertFalse(new ValidChecker().isInvalidPw("1258ffege"));
        assertTrue(new ValidChecker().isInvalidPw("123"));
        assertTrue(new ValidChecker().isInvalidPw("123@"));
        assertTrue(new ValidChecker().isInvalidPw("12345678w!"));
        assertTrue(new ValidChecker().isInvalidPw("qwertyuiop123456"));
        assertTrue(new ValidChecker().isInvalidPw("qwer^"));
    }

    @org.junit.jupiter.api.Test
    void isNameValid() {
        assertTrue(new ValidChecker().isNameValid("qwer"));
        assertTrue(new ValidChecker().isNameValid("qwert"));
        assertTrue(new ValidChecker().isNameValid("qwertyuiopq"));
        assertFalse(new ValidChecker().isNameValid("qwertyu iopq"));
        assertFalse(new ValidChecker().isNameValid("hey1"));
        assertFalse(new ValidChecker().isNameValid("@qwe"));
    }
}