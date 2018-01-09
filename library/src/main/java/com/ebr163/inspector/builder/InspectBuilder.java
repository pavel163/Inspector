package com.ebr163.inspector.builder;

import android.view.View;

import com.ebr163.inspector.inspection.InspectionObject;
import com.ebr163.inspector.rule.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class InspectBuilder<V extends View, Type> {

    private V view;
    private List<Rule<Type>> rules;
    private InspectionObject.OnValueListener<V, Type> valueListener;
    private InspectionObject.OnErrorListener<V> errorListener;

    public InspectBuilder(V view) {
        this.view = view;
        rules = new ArrayList<>();
    }

    public InspectBuilder<V, Type> addRule(Rule<Type> rule) {
        rules.add(rule);
        return this;
    }

    @SafeVarargs
    public final InspectBuilder<V, Type> addRules(Rule<Type>... rules) {
        Collections.addAll(this.rules, rules);
        return this;
    }

    public InspectBuilder<V, Type> addValueListener(InspectionObject.OnValueListener<V, Type> valueListener) {
        this.valueListener = valueListener;
        return this;
    }

    public InspectBuilder<V, Type> addErrorListener(InspectionObject.OnErrorListener<V> errorListener) {
        this.errorListener = errorListener;
        return this;
    }

    public InspectionObject<V, Type> build() {
        InspectionObject<V, Type> inspectionObject = new InspectionObject<>(view, rules, valueListener, errorListener);
        valueListener = null;
        errorListener = null;
        view = null;
        rules = null;
        return inspectionObject;
    }
}
