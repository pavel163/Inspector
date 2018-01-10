package com.ebr163.inspector.inspection;

import com.ebr163.inspector.rule.Rule;

import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class InspectionValue<Type> extends AbstractInspection<Type> {

    private Type value;
    private OnErrorListener errorListener;

    public InspectionValue(Type value, List<Rule<Type>> rules, OnErrorListener errorListener) {
        super(rules);
        this.value = value;
        this.errorListener = errorListener;
    }

    @Override
    public void clear() {
        super.clear();
        errorListener = null;
        value = null;
    }

    @Override
    public boolean inspect() {
        if (errorListener != null){
            errorListener.setErrorEnabled(false, null);
        }

        for (Rule<Type> rule : rules) {
            if (!rule.verify(value)) {
                if (errorListener != null) {
                    errorListener.setErrorEnabled(true, rule.errorMessage());
                }
                return false;
            }
        }

        return true;
    }

    @Override
    public Type getValue() {
        return value;
    }

    @Override
    public void setErrorEnabled(boolean enabled, String error) {
        if (enabled){
            errorListener.setErrorEnabled(true, error);
        } else {
            errorListener.setErrorEnabled(false, null);
        }
    }

    public interface OnErrorListener {
        void setErrorEnabled(boolean enabled, String error);
    }
}
