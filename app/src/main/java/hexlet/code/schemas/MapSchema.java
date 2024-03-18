package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {

    private boolean sizeOfMethodCalled = false;
    private int mapSize = 2147483647;

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

}
