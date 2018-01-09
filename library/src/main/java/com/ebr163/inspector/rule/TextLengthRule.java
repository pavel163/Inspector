package com.ebr163.inspector.rule;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class TextLengthRule extends AbstractRule<CharSequence> {

    private final int length;
    private final TextLength textLength;

    public enum TextLength {
        MORE, LESS, EQUAL
    }

    public TextLengthRule(int length, TextLength textLength, String errorMessage) {
        super(errorMessage);
        this.length = length;
        this.textLength = textLength;
    }

    @Override
    public boolean verify(CharSequence value) {
        if (textLength == TextLength.EQUAL) {
            return value.length() == length;
        } else if (textLength == TextLength.LESS) {
            return value.length() < length;
        } else {
            return value.length() > length;
        }
    }
}
