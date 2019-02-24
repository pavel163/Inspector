//package com.ebr163.inspector.view;
//
//import android.view.View;
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
//@PrepareForTest(InspectionView.class)
//public class InspectionViewTest {
//
//    private InspectionView<View, String> inspectionView;
//    private String value = "test_value";
//    private InspectionView.OnErrorListener<View> errorListener;
//    private InspectionView.OnValueListener<View, String> valueListener;
//    private Rule<String> rule1;
//    private Rule<String> rule2;
//    private View view;
//
//    @Before
//    public void setUp() throws Exception {
//        view = Mockito.mock(View.class);
//        rule1 = Mockito.mock(TextNotEmptyRule.class);
//        rule2 = Mockito.mock(TextNotEmptyRule.class);
//        valueListener = Mockito.mock(InspectionView.OnValueListener.class);
//        doReturn(value).when(valueListener).getValue(view);
//        errorListener = Mockito.mock(InspectionView.OnErrorListener.class);
//    }
//
//    private void initWithErrorListener() {
//        inspectionView = new InspectViewBuilder<View, String>(view)
//                .addErrorListener(errorListener)
//                .addRules(rule1, rule2)
//                .addValueListener(valueListener)
//                .build();
//    }
//
//    private void initWithoutErrorListener() {
//        inspectionView = new InspectViewBuilder<View, String>(view)
//                .addRules(rule1, rule2)
//                .addValueListener(valueListener)
//                .build();
//    }
//
//    @Test
//    public void clear() throws Exception {
//        initWithErrorListener();
//
//        inspectionView.clear();
//
//        assertTrue(((List) Whitebox.getInternalState(inspectionView, "rules")).isEmpty());
//        assertNull(Whitebox.getInternalState(inspectionView, "errorListener"));
//        assertNull(Whitebox.getInternalState(inspectionView, "valueListener"));
//        assertNull(Whitebox.getInternalState(inspectionView, "view"));
//    }
//
//    @Test
//    public void inspectTrue_With_ErrorListener() throws Exception {
//        initWithErrorListener();
//
//        doReturn(true).when(rule1).verify(anyString());
//        doReturn(true).when(rule2).verify(anyString());
//
//        boolean result = inspectionView.inspect();
//
//        assertTrue(result);
//        verify(errorListener).setErrorEnabled(eq(view), (String) any(), eq(false));
//    }
//
//    @Test
//    public void inspectTrue_Without_ErrorListener() throws Exception {
//        initWithoutErrorListener();
//
//        doReturn(true).when(rule1).verify(anyString());
//        doReturn(true).when(rule2).verify(anyString());
//
//        boolean result = inspectionView.inspect();
//
//        assertTrue(result);
//        verify(errorListener, never()).setErrorEnabled(eq(view), (String) any(), eq(false));
//    }
//
//    @Test
//    public void inspectFalse_With_ErrorListener() throws Exception {
//        initWithErrorListener();
//
//        doReturn(false).when(rule1).verify(anyString());
//        doReturn(true).when(rule2).verify(anyString());
//
//        boolean result = inspectionView.inspect();
//
//        assertFalse(result);
//        verify(errorListener).setErrorEnabled(eq(view), (String) any(), eq(false));
//        verify(errorListener).setErrorEnabled(eq(view), (String) any(), eq(true));
//    }
//
//    @Test
//    public void inspectFalse_Without_ErrorListener() throws Exception {
//        initWithoutErrorListener();
//
//        doReturn(false).when(rule1).verify(anyString());
//        doReturn(true).when(rule2).verify(anyString());
//
//        boolean result = inspectionView.inspect();
//
//        assertFalse(result);
//        verify(errorListener, never()).setErrorEnabled(eq(view), (String) any(), anyBoolean());
//    }
//
//
//    @Test
//    public void getValue() throws Exception {
//        initWithErrorListener();
//
//        assertEquals(value, inspectionView.getValue());
//
//        verify(valueListener).getValue(view);
//    }
//
//    @Test
//    public void setErrorEnabled_With_ErrorListener() throws Exception {
//        initWithErrorListener();
//
//        inspectionView.setErrorEnabled(true, "test");
//
//        verify(errorListener).setErrorEnabled(view, "test", true);
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void setErrorEnabled_Without_ErrorListener() throws Exception {
//        initWithoutErrorListener();
//
//        inspectionView.setErrorEnabled(true, "test");
//    }
//}