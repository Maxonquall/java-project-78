package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    private /*static*/ int minLengthOfString = -1;
    private String subString = "";

    public boolean isValid(String string) {
        if ((string == null) || string.isEmpty()) {
            return !requiredCalled;
        }

        return (minLengthOfString == -1 || string.length() >= minLengthOfString)
                && (subString.isEmpty() || string.contains(subString));
    }

    public StringSchema required() {
        requiredCalled = true;
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.minLengthOfString = minLength;
        return this;
    }

    public StringSchema contains(String stringExample) {
        this.subString = stringExample;
        return this;
    }
}
