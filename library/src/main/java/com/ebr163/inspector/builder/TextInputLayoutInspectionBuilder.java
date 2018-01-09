package com.ebr163.inspector.builder;

import android.support.design.widget.TextInputLayout;

import com.ebr163.inspector.inspection.Inspection;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class TextInputLayoutInspectionBuilder extends InspectionBuilder<TextInputLayout, CharSequence> {

    public TextInputLayoutInspectionBuilder(TextInputLayout view) {
        super(view);
        addValueListener(new Inspection.OnValueListener<TextInputLayout, CharSequence>() {
            @Override
            public CharSequence getValue(TextInputLayout view) {
                return view.getEditText().getText();
            }
        });

        addErrorListener(new Inspection.OnErrorListener<TextInputLayout>() {
            @Override
            public void showError(TextInputLayout view, String errorMessage) {
                view.setError(errorMessage);
                view.setErrorEnabled(true);
            }

            @Override
            public void hideError(TextInputLayout view) {
                view.setErrorEnabled(false);
            }
        });
    }
}
