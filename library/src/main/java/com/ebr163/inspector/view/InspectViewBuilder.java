package com.ebr163.inspector.view;

import android.view.View;

import com.ebr163.inspector.rule.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class InspectViewBuilder<V extends View, Type> {

    private V view;
    private List<Rule<Type>> rules;
    private InspectionView.OnValueListener<V, Type> valueListener;
    private InspectionView.OnErrorListener<V> errorListener;
    private boolean enabledCheckAfterLostFocus;
    private Rule<Type> ruleForStartCheckAfterLostFocus;
    private int viewId = -1;

    public InspectViewBuilder(V view) {
        this.view = view;
        rules = new ArrayList<>();
    }

    public InspectViewBuilder<V, Type> addRule(Rule<Type> rule) {
        rules.add(rule);
        return this;
    }

    @SafeVarargs
    public final InspectViewBuilder<V, Type> addRules(Rule<Type>... rules) {
        Collections.addAll(this.rules, rules);
        return this;
    }

    public InspectViewBuilder<V, Type> addValueListener(InspectionView.OnValueListener<V, Type> valueListener) {
        this.valueListener = valueListener;
        return this;
    }

    public InspectViewBuilder<V, Type> addErrorListener(InspectionView.OnErrorListener<V> errorListener) {
        this.errorListener = errorListener;
        return this;
    }

    public InspectViewBuilder<V, Type> checkAfterLostFocus() {
        return checkAfterLostFocus(null);
    }

    public InspectViewBuilder<V, Type> checkAfterLostFocus(Rule<Type> ruleForStartCheck) {
        return checkAfterLostFocus(ruleForStartCheck, -1);
    }

    public InspectViewBuilder<V, Type> checkAfterLostFocus(Rule<Type> ruleForStartCheck, int viewId) {
        enabledCheckAfterLostFocus = true;
        this.ruleForStartCheckAfterLostFocus = ruleForStartCheck;
        this.viewId = viewId;
        return this;
    }

    public InspectionView<V, Type> build() {
        return new InspectionView<>(view, rules, valueListener,
                errorListener, enabledCheckAfterLostFocus, ruleForStartCheckAfterLostFocus,
                viewId);
    }
}
