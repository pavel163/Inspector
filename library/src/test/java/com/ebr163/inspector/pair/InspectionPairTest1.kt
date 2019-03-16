//package com.ebr163.inspector.pair
//
//import android.util.Pair
//
//import com.ebr163.inspector.Inspection
//import com.ebr163.inspector.rule.Rule
//import com.ebr163.inspector.rule.pair.ConfirmPasswordRule
//import com.nhaarman.mockitokotlin2.mock
//
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.powermock.core.classloader.annotations.PrepareForTest
//import org.powermock.modules.junit4.PowerMockRunner
//import org.powermock.reflect.Whitebox
//
//import org.junit.Assert.assertFalse
//import org.junit.Assert.assertNull
//import org.junit.Assert.assertTrue
//import org.mockito.ArgumentMatchers.any
//import org.mockito.ArgumentMatchers.eq
//import org.mockito.Mockito
//import org.mockito.Mockito.doReturn
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.never
//import org.mockito.Mockito.spy
//import org.mockito.Mockito.verify
//
///**
// * Created by Bakht
// * on 11.01.2018.
// */
//@RunWith(PowerMockRunner::class)
//@PrepareForTest(InspectionPair::class)
//class InspectionPairTest1 {
//
//    private var inspectionPair: InspectionPair<String, String>? = null
//    private val value = "test_value"
//    private lateinit var errorListener: (inspections: Pair<Inspection<String>, Inspection<String>>, enabled: Boolean, error: String?) -> Unit
//    private lateinit var rule1: Rule<Pair<String, String>>
//    private lateinit var inspection1: Inspection<String>
//    private lateinit var inspection2: Inspection<String>
//
//    @Before
//    @Throws(Exception::class)
//    fun setUp() {
//        rule1 = mock(ConfirmPasswordRule::class.java)
//        inspection1 = mock()
//        inspection2 = mock()
//        errorListener = mock()
//    }
//
//    private fun initWithErrorListener() {
//        inspectionPair = spy(InspectPairBuilder(inspection1, inspection2)
//                .addErrorListener(errorListener)
//                .addRule(rule1)
//                .build())
//    }
//
//    private fun initWithoutErrorListener() {
//        inspectionPair = spy(InspectPairBuilder(inspection1, inspection2)
//                .addRule(rule1)
//                .build())
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun clear() {
//        initWithErrorListener()
//
//        inspectionPair!!.clear()
//
//        assertTrue((Whitebox.getInternalState<Any>(inspectionPair!!, "rules") as List<*>).isEmpty())
//        assertNull(Whitebox.getInternalState(inspectionPair!!, "errorListener"))
//        verify<Inspection<String>>(inspection1).clear()
//        verify<Inspection<String>>(inspection2).clear()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun inspectTrue_With_ErrorListener() {
//        initWithErrorListener()
//
//        doReturn(true).`when`<Inspection<String>>(inspection1).inspect()
//        doReturn(true).`when`<Inspection<String>>(inspection2).inspect()
//        doReturn(true).`when`<Rule<Pair<String, String>>>(rule1).verify(any())
//
//        val result = inspectionPair!!.inspect()
//
//        assertTrue(result)
//        verify<InspectionPair<String, String>>(inspectionPair).value
//        verify(errorListener).invoke(any(), eq(false), any<Any>() as String?)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun inspectTrue_Without_ErrorListener() {
//        initWithoutErrorListener()
//
//        doReturn(true).`when`<Inspection<String>>(inspection1).inspect()
//        doReturn(true).`when`<Inspection<String>>(inspection2).inspect()
//        doReturn(true).`when`<Rule<Pair<String, String>>>(rule1).verify(any())
//
//        val result = inspectionPair!!.inspect()
//
//        assertTrue(result)
//        verify<InspectionPair<String, String>>(inspectionPair).value
//        verify(errorListener, never()).invoke(any(), eq(false), any<Any>() as String?)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun inspectFalse_With_ErrorListener() {
//        initWithErrorListener()
//
//        doReturn(true).`when`<Inspection<String>>(inspection1).inspect()
//        doReturn(true).`when`<Inspection<String>>(inspection2).inspect()
//        doReturn(false).`when`<Rule<Pair<String, String>>>(rule1).verify(any())
//
//        val result = inspectionPair!!.inspect()
//
//        assertFalse(result)
//        verify<InspectionPair<String, String>>(inspectionPair).value
//        verify(errorListener).invoke(any(), eq(false), any<Any>() as String?)
//        verify(errorListener).invoke(any(), eq(true), any<Any>() as String?)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun inspectFalse_Without_ErrorListener() {
//        initWithoutErrorListener()
//
//        doReturn(true).`when`<Inspection<String>>(inspection1).inspect()
//        doReturn(true).`when`<Inspection<String>>(inspection2).inspect()
//        doReturn(false).`when`<Rule<Pair<String, String>>>(rule1).verify(any())
//
//        val result = inspectionPair!!.inspect()
//
//        assertFalse(result)
//        verify<InspectionPair<String, String>>(inspectionPair).value
//        verify(errorListener, never()).invoke(any(), eq(false), any<Any>() as String?)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun inspectFalse() {
//        initWithoutErrorListener()
//
//        doReturn(true).`when`<Inspection<String>>(inspection1).inspect()
//        doReturn(false).`when`<Inspection<String>>(inspection2).inspect()
//
//        val result = inspectionPair!!.inspect()
//
//        assertFalse(result)
//        verify<InspectionPair<String, String>>(inspectionPair, never()).value
//        verify<Rule<Pair<String, String>>>(rule1, never()).verify(any())
//        verify(errorListener, never()).invoke(any(), eq(false), any<Any>() as String?)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun setErrorEnabled_With_ErrorListener() {
//        initWithErrorListener()
//
//        inspectionPair!!.setErrorEnabled(true, "test")
//
//        verify(errorListener).invoke(any(), eq(true), eq("test"))
//    }
//}