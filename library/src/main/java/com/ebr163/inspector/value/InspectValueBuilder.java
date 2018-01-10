package com.ebr163.inspector.value;

import com.ebr163.inspector.rule.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class InspectValueBuilder<Type> {

    private Type value;
    private List<Rule<Type>> rules;
    private InspectionValue.OnErrorListener errorListener;

    public InspectValueBuilder(Type value) {
        this.value = value;
        rules = new ArrayList<>();
    }

    public InspectValueBuilder<Type> addRule(Rule<Type> rule) {
        rules.add(rule);
        return this;
    }

    @SafeVarargs
    public final InspectValueBuilder<Type> addRules(Rule<Type>... rules) {
        Collections.addAll(this.rules, rules);
        return this;
    }

    public InspectValueBuilder<Type> addErrorListener(InspectionValue.OnErrorListener errorListener) {
        this.errorListener = errorListener;
        return this;
    }

    public InspectionValue<Type> build() {
        InspectionValue<Type> inspectionValue = new InspectionValue<>(value, rules, errorListener);
        errorListener = null;
        value = null;
        rules = null;
        return inspectionValue;
    }
}