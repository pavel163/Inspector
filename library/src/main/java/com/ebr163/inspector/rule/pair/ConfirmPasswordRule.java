package com.ebr163.inspector.rule.pair;

import android.util.Pair;

import com.ebr163.inspector.rule.Rule;

/**
 * Created by Bakht
 * on 10.01.2018.
 */

public class ConfirmPasswordRule implements Rule<Pair<String, String>> {

    @Override
    public boolean verify(Pair<String, String> value) {
        return value.first.equals(value.second);
    }

    @Override
    public String errorMessage() {
        return "Пароли не совпадают";
    }
}
