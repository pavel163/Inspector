package com.ebr163.inspector.rule

import java.util.regex.Pattern

/**
 * Created by Bakht
 * on 11.01.2018.
 */

class RegexpRule(private val regexpPattern: String, errorMessage: String) : AbstractRule<String>(errorMessage) {

    override fun verify(value: String?): Boolean = Pattern.compile(regexpPattern).matcher(value!!).matches()
}
