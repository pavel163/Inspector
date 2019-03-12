package com.ebr163.inspector.variable

import com.ebr163.inspector.rule.Rule
import com.ebr163.inspector.rule.RuleBuilderDsl

class InspectionVariableBuilderDsl<Type> {

    var value: Type? = null
    private var errorListener: ((enabled: Boolean, errorMessage: String?) -> Unit)? = null
    private val rules: MutableList<Rule<Type>> = mutableListOf()

    fun rule(rule: Rule<Type>) = rules.add(rule)

//    fun rule(init: RuleBuilderDsl<Type>.() -> Unit) {
//        val ruleBuilderDsl = RuleBuilderDsl<Type>()
//        ruleBuilderDsl.init()
//        rules.add(ruleBuilderDsl.build())
//    }

    fun onErrorListener(errorListener: (enabled: Boolean, errorMessage: String?) -> Unit) {
        this.errorListener = errorListener
    }

    fun build(): InspectionVariable<Type> = InspectionVariable(value, rules, errorListener)
}