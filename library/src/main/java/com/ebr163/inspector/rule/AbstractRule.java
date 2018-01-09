package com.ebr163.inspector.rule;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public abstract class AbstractRule<T> implements Rule<T> {

    private final String errorMessage;

    protected AbstractRule(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}