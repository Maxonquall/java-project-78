package hexlet.code;


import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
        schema.isValid(4);
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
    @Test
    void requiredMapTest() {
        var v = new Validator();
        var schema = v.map();
        var actual1 = schema.isValid(null);
        assertTrue(actual1);
        schema.required();
        actual1 = schema.isValid(null);
        assertFalse(actual1);
    }
    @Test
    void sizeOfMethodMapTest() {
        var v = new Validator();
        var schema = v.map();
        var actual1 = schema.isValid(Map.of("key1", "value1"));
        var actual2 = schema.isValid(Map.of("key1", "value1", "key2", "value2"));
        assertTrue(actual1);
        assertTrue(actual2);
        schema.sizeof(2);
        actual1 = schema.isValid(Map.of("key1", "value1"));
        actual2 = schema.isValid(Map.of("key1", "value1", "key2", "value2"));
        assertFalse(actual1);
        assertTrue(actual2);
    }
    @Test
    void methodChainingMapTest() {
        var v = new Validator();
        var schema = v.map().required().sizeof(1);
        var actual1 = schema.isValid(null);
        var actual2 = schema.isValid(Map.of("key1", "value1", "key2", "value2"));
        var actual3 = schema.isValid(Map.of("key1", "value1"));
        assertFalse(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
    }

    @Test
    void nestedMapStringTest() {
        var v = new Validator();
        var schema = v.map();
        var schemas = new HashMap<String, BaseSchema<String>>();
        schemas.put("String1", v.string().required().contains("Valid"));
        schemas.put("String2", v.string());
        schema.shape(schemas);

        var map1 = new HashMap<String, String>();
        map1.put("String1", "Validation");
        map1.put("String2", null);
        var actual1 = schema.isValid(map1);

        var map2 = new HashMap<String, String>();
        map2.put("String1", "validation");
        map2.put("String2", null);
        var actual2 = schema.isValid(map2);

        var map3 = new HashMap<String, String>();
        map3.put("String1", null);
        map3.put("String2", "Valid");
        var actual3 = schema.isValid(map3);

        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);

    }

    @Test
    void nestedMapNumberTest() {
        var v = new Validator();
        var schema = v.map();
        var schemas = new HashMap<String, BaseSchema<Integer>>();
        schemas.put("Number1", v.number().required().positive().range(1, 100));
        schemas.put("Number2", v.number().positive());
        schema.shape(schemas);

        var map1 = new HashMap<String, Integer>();
        map1.put("Number1", 2);
        map1.put("Number2", null);
        var actual1 = schema.isValid(map1);

        var map2 = new HashMap<String, Integer>();
        map2.put("Number1", null);
        map2.put("Number2", 2);
        var actual2 = schema.isValid(map2);

        var map3 = new HashMap<String, Integer>();
        map3.put("Number1", -2);
        map3.put("Number2", 665);
        var actual3 = schema.isValid(map3);

        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
    }

    @Test
    void nestedMapMapTest() {
        var v = new Validator();
        var schema = v.map();
        var schemas = new HashMap<String, BaseSchema<Map>>();
        schemas.put("Map1", v.map().required().sizeof(2));
        schemas.put("Map2", v.map().sizeof(2));
        schema.shape(schemas);

        var map1 = new HashMap<String, Map<String, String>>();
        map1.put("Map1", Map.of("key1", "value1", "key2", "value2"));
        map1.put("Map2", null);
        var actual1 = schema.isValid(map1);

        var map2 = new HashMap<String, Map<String, String>>();
        map2.put("Map1", null);
        map2.put("Map2", Map.of("key1", "value1", "key2", "value2"));
        var actual2 = schema.isValid(map2);

        var map3 = new HashMap<String, Map<String, String>>();
        map3.put("Map1", Map.of("key1", "value1"));
        map3.put("Map2", Map.of("key1", "value1", "key2", "value2"));
        var actual3 = schema.isValid(map3);

        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
    }

}
