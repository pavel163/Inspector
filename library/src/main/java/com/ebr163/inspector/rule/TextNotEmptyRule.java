package com.ebr163.inspector.rule;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class TextNotEmptyRule extends AbstractRule<String> {

    public TextNotEmptyRule(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean verify(String value) {
        return value != null && value.length() != 0;
    }
}