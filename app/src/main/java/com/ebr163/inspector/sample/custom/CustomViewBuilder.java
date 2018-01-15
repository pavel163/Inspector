package com.ebr163.inspector.sample.custom;

import android.widget.EditText;

import com.ebr163.inspector.view.InspectViewBuilder;

/**
 * Created by Bakht
 * on 15.01.2018.
 */

public class CustomViewBuilder extends InspectViewBuilder<EditText, String> {

    public CustomViewBuilder(EditText view) {
        super(view);
        addValueListener(view1 -> null);

        addErrorListener((view12, error, enabled) -> {
        });
    }
}
