package com.ebr163.inspector.view

import android.view.View

import com.ebr163.inspector.rule.Rule

import java.util.ArrayList
import java.util.Collections

/**
 * Created by Bakht
 * on 09.01.2018.
 */

open class InspectViewBuilder<V : View, Type>(private val view: V) {

    private val rules: MutableList<Rule<Type>> = mutableListOf()
    private var valueListener: ((view: V) -> Type)? = null
    private var errorListener: ((view: V?, error: String?, enabled: Boolean) -> Unit)? = null
    private var enabledCheckAfterLostFocus: Boolean = false
    private var ruleForStartCheckAfterLostFocus: Rule<Type>? = null
    private var viewId = -1

    fun addRule(rule: Rule<Type>): InspectViewBuilder<V, Type> {
        rules.add(rule)
        return this
    }

    @SafeVarargs
    fun addRules(vararg rules: Rule<Type>): InspectViewBuilder<V, Type> {
        Collections.addAll(this.rules, *rules)
        return this
    }

    fun addValueListener(valueListener: (view: V) -> Type): InspectViewBuilder<V, Type> {
        this.valueListener = valueListener
        return this
    }

    fun addErrorListener(errorListener: (view: V?, error: String?, enabled: Boolean) -> Unit): InspectViewBuilder<V, Type> {
        this.errorListener = errorListener
        return this
    }

    @JvmOverloads
    fun checkAfterLostFocus(ruleForStartCheck: Rule<Type>? = null, viewId: Int = -1): InspectViewBuilder<V, Type> {
        enabledCheckAfterLostFocus = true
        this.ruleForStartCheckAfterLostFocus = ruleForStartCheck
        this.viewId = viewId
        return this
    }

    fun build(): InspectionView<V, Type> {
        return InspectionView(
                view,
                rules,
                valueListener,
                errorListener,
                enabledCheckAfterLostFocus,
                ruleForStartCheckAfterLostFocus,
                viewId
        )
    }
}
