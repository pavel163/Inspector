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
        private var errorListener: ((showError: Boolean, String?) -> Unit)?
) : AbstractInspection<Type>(rules) {

    override fun clear() {
        super.clear()
        errorListener = null
        value = null
    }

    override fun inspect(): Boolean {
        errorListener?.invoke(false, null)

        rules.forEach { rule ->
            if (!rule.verify(value)) {
                errorListener?.invoke(true, rule.errorMessage())
                return false
            }
        }

        return true
    }

    override fun setErrorEnabled(showError: Boolean, errorMessage: String?) {
        if (showError) {
            errorListener?.invoke(true, errorMessage)
        } else {
            errorListener?.invoke(false, null)
        }
    }
}
