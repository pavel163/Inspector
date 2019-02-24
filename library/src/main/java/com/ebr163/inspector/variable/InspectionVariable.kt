package com.ebr163.inspector.variable

import com.ebr163.inspector.AbstractInspection
import com.ebr163.inspector.rule.Rule

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class InspectionVariable<Type>(
        override var value: Type?,
        rules: MutableList<Rule<Type>>,
        private var errorListener: ((Boolean, String?) -> Unit)?
) : AbstractInspection<Type>(rules) {

    override fun clear() {
        super.clear()
        errorListener = null
        value = null
    }

    override fun inspect(): Boolean {
        errorListener?.invoke(false, null)

        for (rule in rules) {
            if (!rule.verify(value)) {
                errorListener?.invoke(true, rule.errorMessage())
                return false
            }
        }

        return true
    }

    override fun setErrorEnabled(enabled: Boolean, error: String) {
        if (enabled) {
            errorListener?.invoke(true, error)
        } else {
            errorListener?.invoke(false, null)
        }
    }
}
