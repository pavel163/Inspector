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
    private View.OnFocusChangeListener currentFocusChangeListener;
    private int viewId;

    InspectionView(V view, List<Rule<Type>> rules,
                   OnValueListener<V, Type> valueListener, OnErrorListener<V> errorListener,
                   boolean enabledCheckAfterLostFocus, final Rule<Type> ruleForStartCheckAfterLostFocus, int viewId) {
        super(rules);
        this.view = view;
        this.valueListener = valueListener;
        this.errorListener = errorListener;
        this.viewId = viewId;

        if (enabledCheckAfterLostFocus) {
            View view1;
            if (viewId == -1){
                view1 = view;
            } else {
                view1 = view.findViewById(viewId);
            }

            currentFocusChangeListener = view1.getOnFocusChangeListener();
            view1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus && (ruleForStartCheckAfterLostFocus == null || ruleForStartCheckAfterLostFocus.verify(getValue()))) {
                        inspect();
                    }
                    if (currentFocusChangeListener != null){
                        currentFocusChangeListener.onFocusChange(view, hasFocus);
                    }
                }
            });
        }
    }

    @Override
    public void clear() {
        super.clear();
        currentFocusChangeListener = null;
        if (viewId != -1){
            view.findViewById(viewId).setOnFocusChangeListener(null);
        } else {
            view.setOnFocusChangeListener(null);
        }
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
        if (enabled) {
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