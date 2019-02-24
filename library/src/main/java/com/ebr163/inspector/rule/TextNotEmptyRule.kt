package com.ebr163.inspector.rule

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class TextNotEmptyRule(errorMessage: String) : AbstractRule<String>(errorMessage) {

    override fun verify(value: String?): Boolean = !value.isNullOrEmpty()
}