package com.ebr163.inspector.rule

class RuleBuilderDsl<Type>() {

    var type: Rule<Type>? = null

    fun build(): Rule<Type> {
        return type!!
    }
}