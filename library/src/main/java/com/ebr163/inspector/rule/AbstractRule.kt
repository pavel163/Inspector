package com.ebr163.inspector.rule

/**
 * Created by Bakht
 * on 09.01.2018.
 */

abstract class AbstractRule<T> protected constructor(private val errorMessage: String = "") : Rule<T> {

    override fun errorMessage(): String = errorMessage
}