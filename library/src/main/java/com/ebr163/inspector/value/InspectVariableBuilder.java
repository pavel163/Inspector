package com.ebr163.inspector.value;

import com.ebr163.inspector.rule.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class InspectVariableBuilder<Type> {

    private Type value;
    private List<Rule<Type>> rules;
    private InspectionVariable.OnErrorListener errorListener;

    public InspectVariableBuilder(Type value) {
        this.value = value;
        rules = new ArrayList<>();
    }

    public InspectVariableBuilder<Type> addRule(Rule<Type> rule) {
        rules.add(rule);
        return this;
    }

    @SafeVarargs
    public final InspectVariableBuilder<Type> addRules(Rule<Type>... rules) {
        Collections.addAll(this.rules, rules);
        return this;
    }

    public InspectVariableBuilder<Type> addErrorListener(InspectionVariable.OnErrorListener errorListener) {
        this.errorListener = errorListener;
        return this;
    }

    public InspectionVariable<Type> build() {
        return new InspectionVariable<>(value, rules, errorListener);
    }
}