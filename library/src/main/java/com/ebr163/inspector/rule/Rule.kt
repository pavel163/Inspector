package com.ebr163.inspector.rule

/**
 * Created by Bakht
 * on 09.01.2018.
 */

interface Rule<Type> {

    fun verify(value: Type?): Boolean

    fun errorMessage(): String?
}