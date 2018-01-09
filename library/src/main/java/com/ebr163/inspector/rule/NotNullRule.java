package com.ebr163.inspector.rule;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class NotNullRule<T> extends AbstractRule<T> {

    public NotNullRule(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean verify(T value) {
        return value != null;
    }
}