package com.ebr163.inspector.pair

import android.util.Pair

import com.ebr163.inspector.AbstractInspection
import com.ebr163.inspector.Inspection
import com.ebr163.inspector.rule.Rule

/**
 * Created by Bakht
 * on 10.01.2018.
 */

class InspectionPair<Type1, Type2>(
        private val inspection1: Inspection<Type1>,
        private val inspection2: Inspection<Type2>,
        rules: MutableList<Rule<Pair<Type1, Type2>>>,
        private var errorListener: ((inspections: Pair<Inspection<Type1>, Inspection<Type2>>, enabled: Boolean, error: String?) -> Unit)?
) : AbstractInspection<Pair<Type1, Type2>>(rules) {

    override val value: Pair<Type1, Type2>?
        get() = Pair<Type1, Type2>(inspection1.value, inspection2.value)

    override fun inspect(): Boolean {
        if (inspection1.inspect() && inspection2.inspect()) {
            errorListener?.invoke(Pair(inspection1, inspection2), false, null)

            rules.forEach { rule ->
                if (!rule.verify(value)) {
                    errorListener?.invoke(Pair(inspection1, inspection2), true, rule.errorMessage())
                    return false
                }
            }

            return true
        } else {
            return false
        }
    }

    override fun clear() {
        super.clear()
        inspection1.clear()
        inspection2.clear()
        errorListener = null
    }

    override fun setErrorEnabled(showError: Boolean, errorMessage: String?) {
        if (showError) {
            errorListener?.invoke(Pair(inspection1, inspection2), true, errorMessage)
        } else {
            errorListener?.invoke(Pair(inspection1, inspection2), false, null)
        }
    }
}
