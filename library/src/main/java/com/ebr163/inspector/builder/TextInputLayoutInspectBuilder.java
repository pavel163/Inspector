package com.ebr163.inspector.builder;

import android.support.design.widget.TextInputLayout;

import com.ebr163.inspector.inspection.InspectionObject;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class TextInputLayoutInspectBuilder extends InspectBuilder<TextInputLayout, String> {

    public TextInputLayoutInspectBuilder(TextInputLayout view) {
        super(view);
        addValueListener(new InspectionObject.OnValueListener<TextInputLayout, String>() {
            @Override
            public String getValue(TextInputLayout view) {
                CharSequence charSequence = view.getEditText().getText();
                if (charSequence != null) {
                    return charSequence.toString();
                } else {
                    return null;
                }
            }
        });

        addErrorListener(new InspectionObject.OnErrorListener<TextInputLayout>() {
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
