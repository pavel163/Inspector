package com.ebr163.inspector.inspection;

import android.util.Pair;

import com.ebr163.inspector.rule.Rule;

import java.util.List;

/**
 * Created by Bakht
 * on 10.01.2018.
 */

public class InspectionPair<Type1, Type2> extends AbstractInspection<Pair<Type1, Type2>> {

    private Inspection<Type1> inspection1;
    private Inspection<Type2> inspection2;
    private OnErrorListener<Type1, Type2> errorListener;

    InspectionPair(Inspection<Type1> inspection1, Inspection<Type2> inspection2,
                   List<Rule<Pair<Type1, Type2>>> rules, OnErrorListener<Type1, Type2> errorListener) {
        super(rules);
        this.inspection1 = inspection1;
        this.inspection2 = inspection2;
        this.errorListener = errorListener;
    }

    @Override
    public boolean inspect() {
        if (errorListener != null) {
            errorListener.setErrorEnabled(new Pair<>(inspection1, inspection2), false, null);
        }

        for (Rule<Pair<Type1, Type2>> rule : rules) {
            if (!rule.verify(getValue())) {
                if (errorListener != null) {
                    errorListener.setErrorEnabled(new Pair<>(inspection1, inspection2), true, rule.errorMessage());
                }
                return false;
            }
        }

        return true;
    }

    @Override
    public Pair<Type1, Type2> getValue() {
        return new Pair<>(inspection1.getValue(), inspection2.getValue());
    }

    @Override
    public void setErrorEnabled(boolean enabled, String error) {

    }

    public interface OnErrorListener<Type1, Type2> {
        void setErrorEnabled(Pair<Inspection<Type1>, Inspection<Type2>> inspections, boolean enabled, String error);
    }
}
