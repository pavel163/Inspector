package com.ebr163.inspector.rule;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class TextLengthRule extends AbstractRule<String> {

    private final int length;
    private final TextLength textLength;

    public enum TextLength {
        MORE, LESS, EQUAL, MORE_OR_EQUAL, LESS_OR_EQUAL
    }

    public TextLengthRule(int length, TextLength textLength, String errorMessage) {
        super(errorMessage);
        this.length = length;
        this.textLength = textLength;
    }

    @Override
    public boolean verify(String value) {
        if (value == null) {
            return false;
        }

        if (textLength == TextLength.EQUAL) {
            return value.length() == length;
        } else if (textLength == TextLength.LESS) {
            return value.length() < length;
        } else if (textLength == TextLength.MORE) {
            return value.length() > length;
        } else if (textLength == TextLength.LESS_OR_EQUAL) {
            return value.length() <= length;
        } else {
            return value.length() >= length;
        }
    }
}
