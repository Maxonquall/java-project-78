package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    private int minLengthOfString = -1;
    private String subString = "";

    /**
     * Validates a string according to certain criteria.
     * @param string
     * @return true if the string is valid, false otherwise
     */
    public boolean isValid(String string) {
        if ((string == null) || string.isEmpty()) {
            return !requiredCalled;
        }

        return (minLengthOfString == -1 || string.length() >= minLengthOfString)
                && (subString.isEmpty() || string.contains(subString));
    }

    /**
     * Prohibits the use of null and the empty string
     * @return StringSchema object
     */

    public StringSchema required() {
        requiredCalled = true;
        return this;
    }

    /**
     * Limits a string by length
     * @param minLength
     * @return StringSchema object
     */

    public StringSchema minLength(int minLength) {
        minLengthOfString = minLength;
        return this;
    }

    /**
     * Checks for the presence of a substring in a string
     * @param stringExample
     * @return StringSchema object
     */
    public StringSchema contains(String stringExample) {
        subString = stringExample;
        return this;
    }
}
