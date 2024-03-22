package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    public Validator() { }
    /**
     * Returns a StringSchema instance that can be used to validate strings.
     * @return StringSchema instance for validating strings.
     */
    public StringSchema string() {
        return new StringSchema();
    }

    /**
     * Returns a NumberSchema instance that can be used to validate strings.
     * @return StringSchema instance for validating strings.
     */

    public NumberSchema number() {
        return new NumberSchema();
    }

    /**
     * Returns a MapSchema instance that can be used to validate strings.
     * @return MapSchema instance for validating strings.
     */

    public MapSchema map() {
        return new MapSchema();
    }
}
