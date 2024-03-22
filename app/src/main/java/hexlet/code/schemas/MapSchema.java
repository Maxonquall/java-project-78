package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;


public class MapSchema extends BaseSchema<Map> {

    private int mapSize = 2147483647;
    private Map<String, BaseSchema> nestedMap = new HashMap<>();

    public boolean isValid(Map map) {
        if (map == null) {
            return !requiredCalled;
        }

        if (!nestedMap.isEmpty()) {
            for (var entry : nestedMap.entrySet()) {
                var key = entry.getKey();
                var value = entry.getValue();
                if (!map.containsKey(key) || !value.isValid(map.get(key))) {
                    return false;
                }
            }
        }

        return (mapSize == 2147483647 || map.size() == mapSize);
    }
    public MapSchema required() {
        requiredCalled = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        mapSize = size;
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        nestedMap.clear();
        nestedMap.putAll(schemas);
        return this;
    }
}
