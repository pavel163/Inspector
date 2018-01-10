package com.ebr163.inspector.rule;

import android.util.Pair;

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
