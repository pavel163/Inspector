package com.ebr163.inspector.inspection;

import android.view.View;

import com.ebr163.inspector.rule.Rule;

import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class Inspection<V extends View, Type> {

    private List<Rule<Type>> rules;
    private V view;
    private OnValueListener<V, Type> valueListener;
    private OnErrorListener<V> errorListener;

    public Inspection(V view, List<Rule<Type>> rules, OnValueListener<V, Type> valueListener, OnErrorListener<V> errorListener) {
        this.view = view;
        this.valueListener = valueListener;
        this.errorListener = errorListener;
        this.rules = rules;
    }

    public void unbind() {
        view = null;
        valueListener = null;
        errorListener = null;
        rules.clear();
    }

    public void inspect() {
        errorListener.hideError(view);

        for (Rule<Type> rule : rules) {
            if (!rule.verify(valueListener.getValue(view))) {
                errorListener.showError(view, rule.errorMessage());
                break;
            }
        }
    }

    public interface OnValueListener<V, Type> {
        Type getValue(V view);
    }

    public interface OnErrorListener<V> {

        void showError(V view, String errorMessage);

        void hideError(V view);
    }
}