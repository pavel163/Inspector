package com.ebr163.inspector.rule

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class NotNullRule<T>(errorMessage: String) : AbstractRule<T>(errorMessage) {

    override fun verify(value: T?): Boolean = value != null
}