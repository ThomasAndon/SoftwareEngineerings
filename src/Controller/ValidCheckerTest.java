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

    }

    @org.junit.jupiter.api.Test
    void isInvalidPw() {

    }

    @org.junit.jupiter.api.Test
    void isNameValid() {
    }

    @Test
    void test(){


    }
}