//package com.ebr163.inspector.pair;
//
//import android.util.Pair;
//
//import com.ebr163.inspector.Inspection;
//import com.ebr163.inspector.rule.Rule;
//import com.ebr163.inspector.rule.pair.ConfirmPasswordRule;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.powermock.reflect.Whitebox;
//
//import java.util.List;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.spy;
//import static org.mockito.Mockito.verify;
//
///**
// * Created by Bakht
// * on 11.01.2018.
// */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(InspectionPair.class)
//public class InspectionPairTest {
//
//    private InspectionPair<String, String> inspectionPair;
//    private String value = "test_value";
//    private InspectionPair.OnErrorListener<String, String> errorListener;
//    private Rule<Pair<String, String>> rule1;
//    private Inspection<String> inspection1;
//    private Inspection<String> inspection2;
//
//    @Before
//    public void setUp() throws Exception {
//        rule1 = mock(ConfirmPasswordRule.class);
//        inspection1 = mock(Inspection.class);
//        inspection2 = mock(Inspection.class);
//        errorListener = mock(InspectionPair.OnErrorListener.class);
//    }
//
//    private void initWithErrorListener() {
//        inspectionPair = spy(new InspectPairBuilder<>(inspection1, inspection2)
//                .addErrorListener(errorListener)
//                .addRule(rule1)
//                .build());
//    }
//
//    private void initWithoutErrorListener() {
//        inspectionPair = spy(new InspectPairBuilder<>(inspection1, inspection2)
//                .addRule(rule1)
//                .build());
//    }
//
//    @Test
//    public void clear() throws Exception {
//        initWithErrorListener();
//
//        inspectionPair.clear();
//
//        assertTrue(((List) Whitebox.getInternalState(inspectionPair, "rules")).isEmpty());
//        assertNull(Whitebox.getInternalState(inspectionPair, "errorListener"));
//        verify(inspection1).clear();
//        verify(inspection2).clear();
//    }
//
//    @Test
//    public void inspectTrue_With_ErrorListener() throws Exception {
//        initWithErrorListener();
//
//        doReturn(true).when(inspection1).inspect();
//        doReturn(true).when(inspection2).inspect();
//        doReturn(true).when(rule1).verify(any(Pair.class));
//
//        boolean result = inspectionPair.inspect();
//
//        assertTrue(result);
//        verify(inspectionPair).getValue();
//        verify(errorListener).setErrorEnabled(any(Pair.class), eq(false), (String) any());
//    }
//
//    @Test
//    public void inspectTrue_Without_ErrorListener() throws Exception {
//        initWithoutErrorListener();
//
//        doReturn(true).when(inspection1).inspect();
//        doReturn(true).when(inspection2).inspect();
//        doReturn(true).when(rule1).verify(any(Pair.class));
//
//        boolean result = inspectionPair.inspect();
//
//        assertTrue(result);
//        verify(inspectionPair).getValue();
//        verify(errorListener, never()).setErrorEnabled(any(Pair.class), eq(false), (String) any());
//    }
//
//    @Test
//    public void inspectFalse_With_ErrorListener() throws Exception {
//        initWithErrorListener();
//
//        doReturn(true).when(inspection1).inspect();
//        doReturn(true).when(inspection2).inspect();
//        doReturn(false).when(rule1).verify(any(Pair.class));
//
//        boolean result = inspectionPair.inspect();
//
//        assertFalse(result);
//        verify(inspectionPair).getValue();
//        verify(errorListener).setErrorEnabled(any(Pair.class), eq(false), (String) any());
//        verify(errorListener).setErrorEnabled(any(Pair.class), eq(true), (String) any());
//    }
//
//    @Test
//    public void inspectFalse_Without_ErrorListener() throws Exception {
//        initWithoutErrorListener();
//
//        doReturn(true).when(inspection1).inspect();
//        doReturn(true).when(inspection2).inspect();
//        doReturn(false).when(rule1).verify(any(Pair.class));
//
//        boolean result = inspectionPair.inspect();
//
//        assertFalse(result);
//        verify(inspectionPair).getValue();
//        verify(errorListener, never()).setErrorEnabled(any(Pair.class), eq(false), (String) any());
//    }
//
//    @Test
//    public void inspectFalse() throws Exception {
//        initWithoutErrorListener();
//
//        doReturn(true).when(inspection1).inspect();
//        doReturn(false).when(inspection2).inspect();
//
//        boolean result = inspectionPair.inspect();
//
//        assertFalse(result);
//        verify(inspectionPair, never()).getValue();
//        verify(rule1, never()).verify(any(Pair.class));
//        verify(errorListener, never()).setErrorEnabled(any(Pair.class), eq(false), (String) any());
//    }
//
//    @Test
//    public void setErrorEnabled_With_ErrorListener() throws Exception {
//        initWithErrorListener();
//
//        inspectionPair.setErrorEnabled(true, "test");
//
//        verify(errorListener).setErrorEnabled(any(Pair.class), eq(true), eq("test"));
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void setErrorEnabled_Without_ErrorListener() throws Exception {
//        initWithoutErrorListener();
//
//        inspectionPair.setErrorEnabled(true, "test");
//    }
//}