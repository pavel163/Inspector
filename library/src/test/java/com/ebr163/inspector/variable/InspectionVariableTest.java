//package com.ebr163.inspector.variable;
//
//import com.ebr163.inspector.rule.Rule;
//import com.ebr163.inspector.rule.TextNotEmptyRule;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.powermock.reflect.Whitebox;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyBoolean;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//
///**
// * Created by Bakht
// * on 11.01.2018.
// */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(InspectionVariable.class)
//public class InspectionVariableTest {
//
//    private InspectionVariable<String> inspectionVariable;
//    private String value = "test_value";
//    private InspectionVariable.OnErrorListener errorListener;
//    private Rule<String> rule1;
//    private Rule<String> rule2;
//
//    @Before
//    public void setUp() throws Exception {
//        rule1 = Mockito.mock(TextNotEmptyRule.class);
//        rule2 = Mockito.mock(TextNotEmptyRule.class);
//        errorListener = Mockito.mock(InspectionVariable.OnErrorListener.class);
//    }
//
//    private void initWithErrorListener(){
//        inspectionVariable = new InspectVariableBuilder<>(value)
//                .addErrorListener(errorListener)
//                .addRules(rule1, rule2)
//                .build();
//    }
//
//    private void initWithoutErrorListener(){
//        inspectionVariable = new InspectVariableBuilder<>(value)
//                .addRules(rule1, rule2)
//                .build();
//    }
//
//    @Test
//    public void clear() throws Exception {
//        initWithErrorListener();
//
//        inspectionVariable.clear();
//
//        assertTrue(((List) Whitebox.getInternalState(inspectionVariable, "rules")).isEmpty());
//        assertNull(Whitebox.getInternalState(inspectionVariable, "errorListener"));
//        assertNull(Whitebox.getInternalState(inspectionVariable, "value"));
//    }
//
//    @Test
//    public void inspectTrue_With_ErrorListener() throws Exception {
//        initWithErrorListener();
//
//        doReturn(true).when(rule1).verify(anyString());
//        doReturn(true).when(rule2).verify(anyString());
//
//        boolean result = inspectionVariable.inspect();
//
//        assertTrue(result);
//        verify(errorListener).setErrorEnabled(false, null);
//    }
//
//    @Test
//    public void inspectTrue_Without_ErrorListener() throws Exception {
//        initWithoutErrorListener();
//
//        doReturn(true).when(rule1).verify(anyString());
//        doReturn(true).when(rule2).verify(anyString());
//
//        boolean result = inspectionVariable.inspect();
//
//        assertTrue(result);
//        verify(errorListener, never()).setErrorEnabled(false, null);
//    }
//
//    @Test
//    public void inspectFalse_With_ErrorListener() throws Exception {
//        initWithErrorListener();
//
//        doReturn(false).when(rule1).verify(anyString());
//        doReturn(true).when(rule2).verify(anyString());
//
//        boolean result = inspectionVariable.inspect();
//
//        assertFalse(result);
//        verify(errorListener).setErrorEnabled(false, null);
//        verify(errorListener).setErrorEnabled(eq(true), (String) any());
//    }
//
//    @Test
//    public void inspectFalse_Without_ErrorListener() throws Exception {
//        initWithoutErrorListener();
//
//        doReturn(false).when(rule1).verify(anyString());
//        doReturn(true).when(rule2).verify(anyString());
//
//        boolean result = inspectionVariable.inspect();
//
//        assertFalse(result);
//        verify(errorListener, never()).setErrorEnabled(anyBoolean(), (String) any());
//    }
//
//    @Test
//    public void getValue() throws Exception {
//        initWithErrorListener();
//
//        assertEquals(value, inspectionVariable.getValue());
//    }
//
//    @Test
//    public void setErrorEnabled_With_ErrorListener() throws Exception {
//        initWithErrorListener();
//
//        inspectionVariable.setErrorEnabled(true, "test");
//
//        verify(errorListener).setErrorEnabled(true, "test");
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void setErrorEnabled_Without_ErrorListener() throws Exception {
//        initWithoutErrorListener();
//
//        inspectionVariable.setErrorEnabled(true, "test");
//    }
//}