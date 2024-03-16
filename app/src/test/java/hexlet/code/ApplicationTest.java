package hexlet.code;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ApplicationTest {

    @Test
    void requiredTest() {
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
    void minLengthTest() {
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
    void containsTest() {
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
    void methodChainingTest() {
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

}
