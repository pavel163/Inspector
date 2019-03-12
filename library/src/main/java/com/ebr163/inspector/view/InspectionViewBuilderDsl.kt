package com.ebr163.inspector.view

import android.view.View
import com.ebr163.inspector.rule.Rule


class InspectionViewBuilderDsl<V : View, Type> {

    var view: V? = null
    var enabledCheckAfterLostFocus: Boolean = false
    var ruleForStartCheckAfterLostFocus: Rule<Type>? = null
    var focusableViewId: Int = -1
    private var errorListener: ((view: V?, error: String?, enabled: Boolean) -> Unit)? = null
    private var valueListener: ((view: V) -> Type?)? = null
    private val rules: MutableList<Rule<Type>> = mutableListOf()

    fun rule(rule: Rule<Type>) = rules.add(rule)

//    fun rule(init: RuleBuilderDsl<Type>.() -> Unit) {
//        val ruleBuilderDsl = RuleBuilderDsl<Type>()
//        ruleBuilderDsl.init()
//        rules.add(ruleBuilderDsl.build())
//    }

    fun onErrorListener(errorListener: (view: V?, error: String?, enabled: Boolean) -> Unit) {
        this.errorListener = errorListener
    }

    fun onValueListener(valueListener: (view: V) -> Type?){
        this.valueListener = valueListener
    }

    fun build(): InspectionView<V, Type> {
        if (view == null){
            throw IllegalArgumentException("view must not be null")
        }
        return InspectionView(
                view,
                rules,
                valueListener,
                errorListener,
                enabledCheckAfterLostFocus,
                ruleForStartCheckAfterLostFocus,
                focusableViewId
        )
    }
}