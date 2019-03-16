package com.ebr163.inspector.rule

import org.junit.Test

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

/**
 * Created by Bakht
 * on 12.01.2018.
 */
class NotNullRuleTest {

    @Test
    @Throws(Exception::class)
    fun verify() {
        val rule = NotNullRule<String>("error")
        assertFalse(rule.verify(null))
        assertTrue(rule.verify(""))
    }
}