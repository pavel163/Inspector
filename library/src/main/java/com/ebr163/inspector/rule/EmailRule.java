package com.ebr163.inspector.rule;

import android.util.Patterns;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class EmailRule extends AbstractRule<String> {

    public EmailRule(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean verify(String value) {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }
}
