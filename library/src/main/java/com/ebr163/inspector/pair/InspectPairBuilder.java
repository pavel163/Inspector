package com.ebr163.inspector.pair;

import android.util.Pair;

import com.ebr163.inspector.Inspection;
import com.ebr163.inspector.rule.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bakht
 * on 10.01.2018.
 */

public class InspectPairBuilder<Type1, Type2> {

    private Inspection<Type1> inspection1;
    private Inspection<Type2> inspection2;
    private List<Rule<Pair<Type1, Type2>>> rules;
    private InspectionPair.OnErrorListener<Type1, Type2> errorListener;

    public InspectPairBuilder(Inspection<Type1> inspection1, Inspection<Type2> inspection2) {
        this.inspection1 = inspection1;
        this.inspection2 = inspection2;
        rules = new ArrayList<>();
    }

    public InspectPairBuilder<Type1, Type2> addRule(Rule<Pair<Type1, Type2>> rule) {
        rules.add(rule);
        return this;
    }

    @SafeVarargs
    public final InspectPairBuilder<Type1, Type2> addRules(Rule<Pair<Type1, Type2>>... rules) {
        Collections.addAll(this.rules, rules);
        return this;
    }

    public InspectPairBuilder<Type1, Type2> addErrorListener(InspectionPair.OnErrorListener<Type1, Type2> errorListener) {
        this.errorListener = errorListener;
        return this;
    }

    public InspectionPair<Type1, Type2> build() {
        InspectionPair<Type1, Type2> inspectionValue = new InspectionPair<>(inspection1, inspection2, rules, errorListener);
        errorListener = null;
        inspection1 = null;
        inspection2 = null;
        rules = null;
        return inspectionValue;
    }
}
