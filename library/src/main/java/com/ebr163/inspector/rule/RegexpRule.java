package com.ebr163.inspector.rule;

import java.util.regex.Pattern;

/**
 * Created by Bakht
 * on 11.01.2018.
 */

public class RegexpRule extends AbstractRule<String> {

    private final String regexpPattern;

    public RegexpRule(String regexpPattern, String errorMessage) {
        super(errorMessage);
        this.regexpPattern = regexpPattern;
    }

    @Override
    public boolean verify(String value) {
        return Pattern.compile(regexpPattern).matcher(value).matches();
    }
}
