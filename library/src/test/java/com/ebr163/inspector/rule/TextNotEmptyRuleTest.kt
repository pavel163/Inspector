package com.ebr163.inspector.rule

import org.junit.Before
import org.junit.Test

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

/**
 * Created by Bakht
 * on 12.01.2018.
 */
class TextNotEmptyRuleTest {

    private var rule: TextNotEmptyRule? = null

    @Before
    fun setUp() {
        rule = TextNotEmptyRule("error")
    }

    @Test
    @Throws(Exception::class)
    fun verify() {
        assertFalse(rule!!.verify(null))
        assertFalse(rule!!.verify(""))
        assertTrue(rule!!.verify("test"))
    }
}