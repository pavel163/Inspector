package com.ebr163.inspector.builder;

import android.support.design.widget.TextInputLayout;

import com.ebr163.inspector.inspection.Inspection;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class TextInputLayoutInspectBuilder extends InspectBuilder<TextInputLayout, CharSequence> {

    public TextInputLayoutInspectBuilder(TextInputLayout view) {
        super(view);
        addValueListener(new Inspection.OnValueListener<TextInputLayout, CharSequence>() {
            @Override
            public CharSequence getValue(TextInputLayout view) {
                return view.getEditText().getText();
            }
        });

        addErrorListener(new Inspection.OnErrorListener<TextInputLayout>() {
            @Override
            public void setErrorEnabled(TextInputLayout view, String error, boolean enabled) {
                if (enabled) {
                    view.setError(error);
                    view.setErrorEnabled(true);
                } else {
                    view.setErrorEnabled(false);
                }
            }
        });
    }
}
