package com.ebr163.inspector.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.ebr163.inspector.Inspection;
import com.ebr163.inspector.Inspector;
import com.ebr163.inspector.rule.TextNotEmptyRule;
import com.ebr163.inspector.view.InspectViewBuilder;
import com.ebr163.inspector.view.InspectionView;

public class MainActivity extends Activity {

    private Inspector inspector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inspector = new Inspector();

        Inspection<String> inspectionView = new InspectViewBuilder< EditText, String>(new EditText(this))
                .addRule(new TextNotEmptyRule("text is empty"))
                .addValueListener(new InspectionView.OnValueListener<EditText, String>() {
                    @Override
                    public String getValue(EditText view) {
                        return view.getText().toString();
                    }
                })
                .addErrorListener(new InspectionView.OnErrorListener<EditText>() {
                    @Override
                    public void setErrorEnabled(EditText view, String error, boolean enabled) {
                        //do something
                    }
                })
                .build();
    }

    @Override
    protected void onDestroy() {
        inspector.clear();
        super.onDestroy();
    }
}
