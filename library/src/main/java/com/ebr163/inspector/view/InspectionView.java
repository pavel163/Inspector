package com.ebr163.inspector.view;

import android.view.View;

import com.ebr163.inspector.AbstractInspection;
import com.ebr163.inspector.rule.Rule;

import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class InspectionView<V extends View, Type> extends AbstractInspection<Type> {

    private V view;
    private OnValueListener<V, Type> valueListener;
    private OnErrorListener<V> errorListener;

    InspectionView(V view, List<Rule<Type>> rules, OnValueListener<V, Type> valueListener, OnErrorListener<V> errorListener) {
        super(rules);
        this.view = view;
        this.valueListener = valueListener;
        this.errorListener = errorListener;
    }

    @Override
    public void clear() {
        super.clear();
        view = null;
        valueListener = null;
        errorListener = null;
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

    @Override
    public Type getValue() {
        return valueListener.getValue(view);
    }

    @Override
    public void setErrorEnabled(boolean enabled, String error) {
        if (enabled){
            errorListener.setErrorEnabled(view, error, true);
        } else {
            errorListener.setErrorEnabled(view, null, false);
        }
    }

    public interface OnValueListener<V, Type> {
        Type getValue(V view);
    }

    public interface OnErrorListener<V> {
        void setErrorEnabled(V view, String error, boolean enabled);
    }
}