package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean requiredCalled = false;

    public abstract boolean isValid(T value);


}

