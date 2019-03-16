package com.ebr163.inspector.rule

import org.junit.Test

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

/**
 * Created by Bakht
 * on 12.01.2018.
 */
class RegexpRuleTest {
    @Test
    @Throws(Exception::class)
    fun verify() {
        val rule = RegexpRule("[abc]", "error")
        assertTrue(rule.verify("a"))
        assertTrue(rule.verify("b"))
        assertTrue(rule.verify("c"))
        assertFalse(rule.verify("r"))
    }
}