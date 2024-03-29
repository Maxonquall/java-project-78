package hexlet.code.schemas;


import java.util.Map;


public final class MapSchema extends BaseSchema {


    public MapSchema() {
        addCheck("data", value -> value instanceof Map<?, ?> || value == null);
    }

    public MapSchema required() {
        addCheck("required", value -> value instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value != null && ((Map) value).size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck("shape", value -> schemas.entrySet()
                .stream()
                ;


        return this;
    }
}