package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {


    public StringSchema required() {
        addCheck("required", value -> value != null && !value.isEmpty());
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
