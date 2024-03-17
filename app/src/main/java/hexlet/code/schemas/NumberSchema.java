package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private static boolean numberIsPositive = false;
    private int minNumber = -2147483648;
    private int maxNumber = 2147483647;

    public boolean isValid(Integer number) {
        if (number == null) {
            return !requiredCalled;
        }

        return  (!numberIsPositive || number > 0)
                && ((number <= maxNumber && number >= minNumber) || maxNumber == 2147483647);
    }

    public NumberSchema required() {
        requiredCalled = true;
        return this;
    }

    public NumberSchema positive() {
        this.numberIsPositive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.minNumber = min;
        this.maxNumber = max;
        return this;
    }
}
