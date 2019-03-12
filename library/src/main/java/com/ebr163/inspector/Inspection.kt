package com.ebr163.inspector

/**
 * Created by Bakht
 * on 09.01.2018.
 */

interface Inspection<Type> {

    val value: Type?

    fun clear()

    fun inspect(): Boolean

    fun setErrorEnabled(showError: Boolean, errorMessage: String?)
}