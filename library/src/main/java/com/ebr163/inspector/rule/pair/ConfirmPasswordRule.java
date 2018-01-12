package com.ebr163.inspector.rule.pair;

import android.util.Pair;

import com.ebr163.inspector.rule.AbstractRule;

/**
 * Created by Bakht
 * on 10.01.2018.
 */

public class ConfirmPasswordRule extends AbstractRule<Pair<String, String>> {

    protected ConfirmPasswordRule(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean verify(Pair<String, String> value) {
        return value.first.equals(value.second);
    }
}
