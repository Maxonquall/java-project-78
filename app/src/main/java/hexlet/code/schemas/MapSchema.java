package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;


public class MapSchema extends BaseSchema<Map> {

    private int mapSize = 2147483647;
    private Map<String, BaseSchema> nestedMap = new HashMap<>();
    /**
     * Validates a map according to certain criteria.
     *
     * @param map the map to be validated
     * @return true if the map is valid, false otherwise
     */
    @Override
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

    /**
     * Prohibits the use of null and the empty string.
     * @return MapSchema object
     */
    public MapSchema required() {
        requiredCalled = true;
        return this;
    }

    /**
     * Limits the size of the map.
     * @param size
     * @return MapSchema object
     */

    public MapSchema sizeof(int size) {
        mapSize = size;
        return this;
    }

    /**
     * Checks the schema for validity.
     * @param schemas
     * @return MapSchema object
     * @param <T>
     */

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        nestedMap.clear();
        nestedMap.putAll(schemas);
        return this;
    }
}
