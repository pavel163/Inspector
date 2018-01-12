package com.ebr163.inspector.rule;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bakht
 * on 12.01.2018.
 */
public class NotNullRuleTest {

    @Test
    public void verify() throws Exception {
        NotNullRule<String> rule = new NotNullRule<>("error");
        assertFalse(rule.verify(null));
        assertTrue(rule.verify(""));
    }
}