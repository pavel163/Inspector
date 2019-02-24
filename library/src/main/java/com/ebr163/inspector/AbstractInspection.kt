package com.ebr163.inspector

import com.ebr163.inspector.rule.Rule

/**
 * Created by Bakht
 * on 09.01.2018.
 */

abstract class AbstractInspection<Type> protected constructor(protected var rules: MutableList<Rule<Type>>) : Inspection<Type> {

    override fun clear() = rules.clear()
}
