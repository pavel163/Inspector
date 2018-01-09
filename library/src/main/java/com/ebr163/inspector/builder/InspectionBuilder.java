package com.ebr163.inspector.builder;

import android.view.View;

import com.ebr163.inspector.inspection.Inspection;
import com.ebr163.inspector.rule.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class InspectionBuilder<V extends View, Type> {

    private V view;
    private List<Rule<Type>> rules;
    private Inspection.OnValueListener<V, Type> valueListener;
    private Inspection.OnErrorListener<V> errorListener;

    public InspectionBuilder(V view) {
        this.view = view;
        rules = new ArrayList<>();
    }

    public InspectionBuilder<V, Type> addRule(Rule<Type> rule) {
        rules.add(rule);
        return this;
    }

    public InspectionBuilder<V, Type> addValueListener(Inspection.OnValueListener<V, Type> valueListener) {
        this.valueListener = valueListener;
        return this;
    }

    public InspectionBuilder<V, Type> addErrorListener(Inspection.OnErrorListener<V> errorListener) {
        this.errorListener = errorListener;
        return this;
    }

    public Inspection build() {
        Inspection inspection = new Inspection<>(view, rules, valueListener, errorListener);
        valueListener = null;
        errorListener = null;
        view = null;
        return inspection;
    }
}
