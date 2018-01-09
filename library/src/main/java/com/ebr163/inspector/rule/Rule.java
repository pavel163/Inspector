package com.ebr163.inspector.rule;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public interface Rule<Type> {

    boolean verify(Type value);

    String errorMessage();
}