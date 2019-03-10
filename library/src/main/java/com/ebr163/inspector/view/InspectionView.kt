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
        private var errorListener: ((view: V?, error: String?, enabled: Boolean) -> Unit)?,
        enabledCheckAfterLostFocus: Boolean,
        ruleForStartCheckAfterLostFocus: Rule<Type>?,
        private val viewId: Int
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
        if (viewId != -1) {
            view!!.findViewById<View>(viewId).onFocusChangeListener = null
        } else {
            view!!.onFocusChangeListener = null
        }
        view = null
        valueListener = null
        errorListener = null
    }

    override fun inspect(): Boolean {
        errorListener?.invoke(view, null, false)

        for (rule in rules) {
            if (!rule.verify(valueListener?.invoke(view!!))) {
                errorListener?.invoke(view, rule.errorMessage(), true)
                return false
            }
        }
        return true
    }

    override fun setErrorEnabled(enabled: Boolean, error: String) {
        if (enabled) {
            errorListener?.invoke(view, error, true)
        } else {
            errorListener?.invoke(view, null, false)
        }
    }

    interface OnValueListener<V, Type> {
        fun getValue(view: V?): Type
    }

    interface OnErrorListener<V> {
        fun setErrorEnabled(view: V?, error: String?, enabled: Boolean)
    }
}