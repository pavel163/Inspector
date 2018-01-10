package com.ebr163.inspector;

import com.ebr163.inspector.rule.Rule;

import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public abstract class AbstractInspection<Type> implements Inspection<Type> {

    protected List<Rule<Type>> rules;

    protected AbstractInspection(List<Rule<Type>> rules){
        this.rules = rules;
    }

    @Override
    public void clear() {
        rules.clear();
    }
}
