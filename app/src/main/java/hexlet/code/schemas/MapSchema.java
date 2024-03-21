package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.HashMap;
import java.util.Map;


public class MapSchema extends BaseSchema<Map> {

    private boolean sizeOfMethodCalled = false;
    private int mapSize = 2147483647;
    private Map<String, BaseSchema> nestedMap = new HashMap<>();
    private boolean shapeNotCalled = true;


    public boolean isValid(Map map) {
        if (map == null) {
            return !requiredCalled;
        }
        return (mapSize == 2147483647 || map.size() == mapSize);
    }
    public MapSchema required() {
        requiredCalled = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        sizeOfMethodCalled = true;
        mapSize = size;
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        shapeNotCalled = false;


        return this;
    }


}
