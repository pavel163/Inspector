package com.ebr163.inspector.rule

import android.util.Patterns

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class EmailRule(errorMessage: String) : AbstractRule<String>(errorMessage) {

    override fun verify(value: String?): Boolean = Patterns.EMAIL_ADDRESS.matcher(value).matches()
}
