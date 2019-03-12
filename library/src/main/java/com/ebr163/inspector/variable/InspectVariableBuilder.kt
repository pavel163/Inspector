package com.ebr163.inspector.variable


import com.ebr163.inspector.rule.Rule
import java.util.Collections

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class InspectVariableBuilder<Type>(private val value: Type?) {

    private val rules: MutableList<Rule<Type>> = mutableListOf()
    private var errorListener: ((enabled: Boolean, errorMessage: String?) -> Unit)? = null

    fun addRule(rule: Rule<Type>): InspectVariableBuilder<Type> {
        rules.add(rule)
        return this
    }

    @SafeVarargs
    fun addRules(vararg rules: Rule<Type>): InspectVariableBuilder<Type> {
        Collections.addAll(this.rules, *rules)
        return this
    }

    fun addErrorListener(errorListener: (Boolean, String?) -> Unit): InspectVariableBuilder<Type> {
        this.errorListener = errorListener
        return this
    }

    fun build(): InspectionVariable<Type> = InspectionVariable(value, rules, errorListener)
}