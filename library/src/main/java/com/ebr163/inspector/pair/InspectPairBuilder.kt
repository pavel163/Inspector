package com.ebr163.inspector.pair

import android.util.Pair
import com.ebr163.inspector.Inspection
import com.ebr163.inspector.rule.Rule
import java.util.Collections

/**
 * Created by Bakht
 * on 10.01.2018.
 */

class InspectPairBuilder<Type1, Type2>(private val inspection1: Inspection<Type1>, private val inspection2: Inspection<Type2>) {

    private val rules: MutableList<Rule<Pair<Type1, Type2>>> = mutableListOf()
    private var errorListener: ((inspections: Pair<Inspection<Type1>, Inspection<Type2>>, enabled: Boolean, error: String?) -> Unit)? = null

    fun addRule(rule: Rule<Pair<Type1, Type2>>): InspectPairBuilder<Type1, Type2> {
        rules.add(rule)
        return this
    }

    @SafeVarargs
    fun addRules(vararg rules: Rule<Pair<Type1, Type2>>): InspectPairBuilder<Type1, Type2> {
        Collections.addAll(this.rules, *rules)
        return this
    }

    fun addErrorListener(errorListener: (Pair<Inspection<Type1>, Inspection<Type2>>, Boolean, String?) -> Unit): InspectPairBuilder<Type1, Type2> {
        this.errorListener = errorListener
        return this
    }

    fun build(): InspectionPair<Type1, Type2> = InspectionPair(inspection1, inspection2, rules, errorListener)
}
