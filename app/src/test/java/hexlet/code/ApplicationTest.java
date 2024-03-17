package hexlet.code;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ApplicationTest {

    @Test
    void requiredStringTest() {
        var v = new Validator();
        var schema = v.string();
        var actual1 = schema.isValid("");
        var actual2 = schema.isValid(null);
        assertTrue(actual1);
        assertTrue(actual2);
        schema.required();
        actual1 = schema.isValid("");
        actual2 = schema.isValid(null);
        assertFalse(actual1);
        assertFalse(actual2);
    }

    @Test
    void minLengthStringTest() {
        var v = new Validator();
        var schema = v.string().minLength(5);
        var actual1 = schema.isValid("1234");
        var actual2 = schema.isValid("12345");
        var actual3 = schema.isValid("123456");
        assertFalse(actual1);
        assertTrue(actual2);
        assertTrue(actual3);
    }

    @Test
    void containsStringTest() {
        var v = new Validator();
        var schema = v.string().contains("Hur");
        var actual1 = schema.isValid("Hurray!");
        var actual2 = schema.isValid("12345");
        var actual3 = schema.isValid("Hur");
        var actual4 = schema.isValid("Hu");
        assertTrue(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
        assertFalse(actual4);
    }

    @Test
    void methodChainingStringTest() {
        var v = new Validator();
        var schema = v.string().required().minLength(6).contains("Valid");
        var actual1 = schema.isValid(null);
        var actual2 = schema.isValid("Valid");
        var actual3 = schema.isValid("123456");
        var actual4 = schema.isValid("Validator");
        assertFalse(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
        assertTrue(actual4);
    }

    @Test
    void requiredNumberTest() {
        var v = new Validator();
        var schema = v.number();
        var actual1 = schema.isValid(null);
        assertTrue(actual1);
        schema.required();
        actual1 = schema.isValid(null);
        assertFalse(actual1);
    }

    @Test
    void positiveNumberTest() {
        var v = new Validator();
        var schema = v.number();
        var actual1 = schema.isValid(-1);
        var actual2 = schema.isValid(0);
        var actual3 = schema.isValid(1);
        assertTrue(actual1);
        assertTrue(actual2);
        assertTrue(actual3);
        schema.positive();
        actual1 = schema.isValid(-1);
        actual2 = schema.isValid(0);
        actual3 = schema.isValid(1);
        assertFalse(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
    }

    @Test
    void rangeNumberTest() {
        var v = new Validator();
        var schema = v.number().range(7, 29);
        var actual1 = schema.isValid(6);
        var actual2 = schema.isValid(7);
        var actual3 = schema.isValid(15);
        var actual4 = schema.isValid(29);
        var actual5 = schema.isValid(30);
        assertFalse(actual1);
        assertTrue(actual2);
        assertTrue(actual3);
        assertTrue(actual4);
        assertFalse(actual5);
    }

    @Test
    void methodChainingNumberTest() {
        var v = new Validator();
        var schema = v.number().required().positive().range(-2, 5);
        var actual1 = schema.isValid(null);
        var actual2 = schema.isValid(-1);
        var actual3 = schema.isValid(6);
        var actual4 = schema.isValid(1);
        assertFalse(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
        assertTrue(actual4);
    }
}
