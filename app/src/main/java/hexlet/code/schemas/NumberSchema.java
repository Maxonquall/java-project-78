package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean numberIsPositive = false;
    private int minNumber = -2147483648;
    private int maxNumber = 2147483647;

    /**
     * Validates a number according to certain criteria.
     * @param number
     * @return true if the string is valid, false otherwise
     */
    public boolean isValid(Integer number) {
        if (number == null) {
            return !requiredCalled;
        }

        return  (!numberIsPositive || number > 0)
                && ((number <= maxNumber && number >= minNumber) || maxNumber == 2147483647);
    }

    /**
     * Prohibits the use of null and the empty string.
     * @return NumberSchema object
     */

    public NumberSchema required() {
        requiredCalled = true;
        return this;
    }

    /**
     * Checks that a number must be positive.
     * @return NumberSchema object
     */

    public NumberSchema positive() {
        numberIsPositive = true;
        return this;
    }

    /**
     * Checks that a number must be in a given range.
     * @param min
     * @param max
     * @return NumberSchema object
     */

    public NumberSchema range(int min, int max) {
        minNumber = min;
        maxNumber = max;
        return this;
    }
}
