package com.ebr163.inspector.view

import android.view.View

import com.ebr163.inspector.AbstractInspection
import com.ebr163.inspector.rule.Rule

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class InspectionView<V : View, Type>(
        private var view: V?,
        rules: MutableList<Rule<Type>>,
        private var valueListener: ((view: V) -> Type?)?,
        private var errorListener: ((view: V?, error: String?, showError: Boolean) -> Unit)?,
        enabledCheckAfterLostFocus: Boolean,
        ruleForStartCheckAfterLostFocus: Rule<Type>?,
        viewId: Int
) : AbstractInspection<Type>(rules) {

    private var currentFocusChangeListener: View.OnFocusChangeListener? = null

    override val value: Type?
        get() = valueListener?.invoke(view!!)

    init {

        if (enabledCheckAfterLostFocus) {
            val view1: View?
            if (viewId == -1) {
                view1 = view
            } else {
                view1 = view?.findViewById(viewId)
            }

            currentFocusChangeListener = view1?.onFocusChangeListener
            view1?.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
                if (!hasFocus && (ruleForStartCheckAfterLostFocus == null || ruleForStartCheckAfterLostFocus.verify(value))) {
                    inspect()
                }
                currentFocusChangeListener?.onFocusChange(view, hasFocus)
            }
        }
    }

    override fun clear() {
        super.clear()
        currentFocusChangeListener = null
        view = null
        valueListener = null
        errorListener = null
    }

    override fun inspect(): Boolean {
        errorListener?.invoke(view, null, false)

        rules.forEach { rule ->
            if (!rule.verify(valueListener?.invoke(view!!))) {
                errorListener?.invoke(view, rule.errorMessage(), true)
                return false
            }
        }
        return true
    }

    override fun setErrorEnabled(showError: Boolean, errorMessage: String?) {
        if (showError) {
            errorListener?.invoke(view, errorMessage, true)
        } else {
            errorListener?.invoke(view, null, false)
        }
    }
}