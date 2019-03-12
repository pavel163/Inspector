package com.ebr163.inspector.pair

import android.util.Pair
import com.ebr163.inspector.Inspection
import com.ebr163.inspector.rule.Rule

class InspectionPairBuilderDsl<Type1, Type2> {

    private val rules = mutableListOf<Rule<Pair<Type1, Type2>>>()
    private var errorListener: ((inspections: Pair<Inspection<Type1>, Inspection<Type2>>, enabled: Boolean, error: String?) -> Unit)? = null

    var inspections1: Inspection<Type1>? = null
    var inspections2: Inspection<Type2>? = null

    fun onErrorListener(errorListener: (Pair<Inspection<Type1>, Inspection<Type2>>, Boolean, String?) -> Unit) {
        this.errorListener = errorListener
    }

    fun rule(rule: Rule<Pair<Type1, Type2>>) = rules.add(rule)

    fun build(): InspectionPair<Type1, Type2> {
        if (inspections1 == null || inspections2 == null){
            throw IllegalArgumentException("inspection1 or inspection2 must not be null")
        }
        return InspectionPair(inspections1!!, inspections2!!, rules, errorListener)
    }
}