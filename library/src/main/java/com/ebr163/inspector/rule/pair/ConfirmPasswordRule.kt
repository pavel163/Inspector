package com.ebr163.inspector.rule.pair

import android.util.Pair

import com.ebr163.inspector.rule.AbstractRule

/**
 * Created by Bakht
 * on 10.01.2018.
 */

class ConfirmPasswordRule(errorMessage: String) : AbstractRule<Pair<String, String>>(errorMessage) {

    override fun verify(value: Pair<String, String>?): Boolean = value?.first == value?.second
}
