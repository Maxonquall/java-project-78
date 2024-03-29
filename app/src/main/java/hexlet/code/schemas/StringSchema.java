package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        addCheck("data", value -> value instanceof String || value == null);
    }

    public StringSchema required() {
        addCheck("required", value -> value instanceof String && !value.isEmpty());
        requiredCalled = true;
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCheck("minLength", value -> value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String stringExample) {
        addCheck("contains", value -> value.contains(stringExample));
        return this;
    }
}
