package com.ebr163.inspector.rule;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bakht
 * on 12.01.2018.
 */
public class TextLengthRuleTest {

    @Test
    public void verify_Type_Equal() throws Exception {
        TextLengthRule rule = new TextLengthRule(3, TextLengthRule.TextLength.EQUAL, "error");
        assertFalse(rule.verify(null));
        assertFalse(rule.verify("12"));
        assertFalse(rule.verify("1234"));
        assertTrue(rule.verify("123"));
    }

    @Test
    public void verify_Type_Less() throws Exception {
        TextLengthRule rule = new TextLengthRule(3, TextLengthRule.TextLength.LESS, "error");
        assertFalse(rule.verify(null));
        assertTrue(rule.verify("12"));
        assertFalse(rule.verify("1234"));
        assertFalse(rule.verify("123"));
    }

    @Test
    public void verify_Type_More() throws Exception {
        TextLengthRule rule = new TextLengthRule(3, TextLengthRule.TextLength.MORE, "error");
        assertFalse(rule.verify(null));
        assertFalse(rule.verify("12"));
        assertTrue(rule.verify("1234"));
        assertFalse(rule.verify("123"));
    }

    @Test
    public void verify_Type_Less_Or_Equal() throws Exception {
        TextLengthRule rule = new TextLengthRule(3, TextLengthRule.TextLength.LESS_OR_EQUAL, "error");
        assertFalse(rule.verify(null));
        assertTrue(rule.verify("12"));
        assertFalse(rule.verify("1234"));
        assertTrue(rule.verify("123"));
    }

    @Test
    public void verify_Type_More_Or_Equal() throws Exception {
        TextLengthRule rule = new TextLengthRule(3, TextLengthRule.TextLength.MORE_OR_EQUAL, "error");
        assertFalse(rule.verify(null));
        assertFalse(rule.verify("12"));
        assertTrue(rule.verify("1234"));
        assertTrue(rule.verify("123"));
    }
}