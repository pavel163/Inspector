package com.ebr163.inspector.builder;

import android.support.design.widget.TextInputLayout;

import com.ebr163.inspector.inspection.InspectionView;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class TextInputLayoutInspectViewBuilder extends InspectViewBuilder<TextInputLayout, String> {

    public TextInputLayoutInspectViewBuilder(TextInputLayout view) {
        super(view);
        addValueListener(new InspectionView.OnValueListener<TextInputLayout, String>() {
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

        addErrorListener(new InspectionView.OnErrorListener<TextInputLayout>() {
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
