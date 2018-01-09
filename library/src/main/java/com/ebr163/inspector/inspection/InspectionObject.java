package com.ebr163.inspector.inspection;

import android.view.View;

import com.ebr163.inspector.rule.Rule;

import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class InspectionObject<V extends View, Type> implements Inspection {

    private List<Rule<Type>> rules;
    private V view;
    private OnValueListener<V, Type> valueListener;
    private OnErrorListener<V> errorListener;

    public InspectionObject(V view, List<Rule<Type>> rules, OnValueListener<V, Type> valueListener, OnErrorListener<V> errorListener) {
        this.view = view;
        this.valueListener = valueListener;
        this.errorListener = errorListener;
        this.rules = rules;
    }

    @Override
    public void clear() {
        view = null;
        valueListener = null;
        errorListener = null;
        rules.clear();
    }

    @Override
    public boolean inspect() {
        if (errorListener != null) {
            errorListener.setErrorEnabled(view, null, false);
        }

        for (Rule<Type> rule : rules) {
            if (!rule.verify(valueListener.getValue(view))) {
                if (errorListener != null) {
                    errorListener.setErrorEnabled(view, rule.errorMessage(), true);
                }
                return false;
            }
        }
        return true;
    }

    public interface OnValueListener<V, Type> {
        Type getValue(V view);
    }

    public interface OnErrorListener<V> {
        void setErrorEnabled(V view, String error, boolean enabled);
    }
}