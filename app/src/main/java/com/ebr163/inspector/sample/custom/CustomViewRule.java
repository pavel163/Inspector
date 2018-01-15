package com.ebr163.inspector.sample.custom;

import com.ebr163.inspector.rule.AbstractRule;

/**
 * Created by Bakht
 * on 15.01.2018.
 */

public class CustomViewRule extends AbstractRule<String> {

    protected CustomViewRule(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean verify(String value) {
        return false;
    }
}
