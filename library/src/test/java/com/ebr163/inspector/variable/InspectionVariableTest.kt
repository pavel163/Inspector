package com.ebr163.inspector.variable

import com.ebr163.inspector.rule.Rule
import com.ebr163.inspector.rule.TextNotEmptyRule
import com.nhaarman.mockitokotlin2.mock

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.never
import org.mockito.Mockito.verify

/**
 * Created by Bakht
 * on 11.01.2018.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(InspectionVariable::class)
class InspectionVariableTest {

    private lateinit var inspectionVariable: InspectionVariable<String>
    private val value = "test_value"
    private lateinit var errorListener: (showError: Boolean, String?) -> Unit
    private lateinit var rule1: Rule<String>
    private lateinit var rule2: Rule<String>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        rule1 = Mockito.mock(TextNotEmptyRule::class.java)
        rule2 = Mockito.mock(TextNotEmptyRule::class.java)
        errorListener = mock()
    }

    private fun initWithErrorListener() {
        inspectionVariable = InspectVariableBuilder(value)
                .addErrorListener(errorListener)
                .addRules(rule1, rule2)
                .build()
    }

    private fun initWithoutErrorListener() {
        inspectionVariable = InspectVariableBuilder(value)
                .addRules(rule1, rule2)
                .build()
    }

    @Test
    @Throws(Exception::class)
    fun clear() {
        initWithErrorListener()

        inspectionVariable.clear()

        assertTrue((Whitebox.getInternalState<Any>(inspectionVariable, "rules") as List<*>).isEmpty())
        assertNull(Whitebox.getInternalState(inspectionVariable, "errorListener"))
        assertNull(Whitebox.getInternalState(inspectionVariable, "value"))
    }

    @Test
    @Throws(Exception::class)
    fun inspectTrue_With_ErrorListener() {
        initWithErrorListener()

        doReturn(true).`when`<Rule<String>>(rule1).verify(anyString())
        doReturn(true).`when`<Rule<String>>(rule2).verify(anyString())

        val result = inspectionVariable.inspect()

        assertTrue(result)
        verify(errorListener).invoke(false, null)
    }

    @Test
    @Throws(Exception::class)
    fun inspectTrue_Without_ErrorListener() {
        initWithoutErrorListener()

        doReturn(true).`when`<Rule<String>>(rule1).verify(anyString())
        doReturn(true).`when`<Rule<String>>(rule2).verify(anyString())

        val result = inspectionVariable.inspect()

        assertTrue(result)
        verify(errorListener, never()).invoke(false, null)
    }

    @Test
    @Throws(Exception::class)
    fun inspectFalse_With_ErrorListener() {
        initWithErrorListener()

        doReturn(false).`when`<Rule<String>>(rule1).verify(anyString())
        doReturn(true).`when`<Rule<String>>(rule2).verify(anyString())

        val result = inspectionVariable.inspect()

        assertFalse(result)
        verify(errorListener).invoke(false, null)
        verify(errorListener).invoke(eq(true), any<Any>() as String?)
    }

    @Test
    @Throws(Exception::class)
    fun inspectFalse_Without_ErrorListener() {
        initWithoutErrorListener()

        doReturn(false).`when`<Rule<String>>(rule1).verify(anyString())
        doReturn(true).`when`<Rule<String>>(rule2).verify(anyString())

        val result = inspectionVariable.inspect()

        assertFalse(result)
        verify(errorListener, never()).invoke(anyBoolean(), any<Any>() as String?)
    }

    @Test
    @Throws(Exception::class)
    fun getValue() {
        initWithErrorListener()

        assertEquals(value, inspectionVariable.value)
    }

    @Test
    @Throws(Exception::class)
    fun setErrorEnabled_With_ErrorListener() {
        initWithErrorListener()

        inspectionVariable.setErrorEnabled(true, "test")

        verify(errorListener).invoke(true, "test")
    }

}