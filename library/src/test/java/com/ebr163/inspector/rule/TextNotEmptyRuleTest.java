package com.ebr163.inspector.rule;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bakht
 * on 12.01.2018.
 */
public class TextNotEmptyRuleTest {

    private TextNotEmptyRule rule;

    @Before
    public void setUp (){
        rule = new TextNotEmptyRule("error");
    }

    @Test
    public void verify() throws Exception {
        assertFalse(rule.verify(null));
        assertFalse(rule.verify(""));
        assertTrue(rule.verify("test"));
    }
}