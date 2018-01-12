package com.ebr163.inspector.rule;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bakht
 * on 12.01.2018.
 */
public class RegexpRuleTest {
    @Test
    public void verify() throws Exception {
        RegexpRule rule = new RegexpRule("[abc]", "error");
        assertTrue(rule.verify("a"));
        assertTrue(rule.verify("b"));
        assertTrue(rule.verify("c"));
        assertFalse(rule.verify("r"));
    }
}