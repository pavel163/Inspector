package com.ebr163.inspector.builder;

import android.support.v7.widget.ViewStubCompat;

import com.ebr163.inspector.inspection.InspectionObject;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class StubInspectBuilder<T> extends InspectBuilder<ViewStubCompat, T> {

    private final T value;

    public StubInspectBuilder(final T value) {
        super(null);
        this.value = value;
        addValueListener(new InspectionObject.OnValueListener<ViewStubCompat, T>() {
            @Override
            public T getValue(ViewStubCompat view) {
                return StubInspectBuilder.this.value;
            }
        });
    }
}
