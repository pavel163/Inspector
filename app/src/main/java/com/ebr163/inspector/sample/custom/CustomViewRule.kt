package com.ebr163.inspector.sample.custom

import com.ebr163.inspector.rule.AbstractRule

/**
 * Created by Bakht
 * on 15.01.2018.
 */

class CustomViewRule(errorMessage: String) : AbstractRule<String>(errorMessage) {

    override fun verify(value: String?): Boolean = false
}
