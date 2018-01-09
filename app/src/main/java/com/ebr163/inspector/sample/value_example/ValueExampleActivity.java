package com.ebr163.inspector.sample.value_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ebr163.inspector.Inspector;
import com.ebr163.inspector.builder.InspectValueBuilder;
import com.ebr163.inspector.inspection.InspectionValue;
import com.ebr163.inspector.rule.TextNotEmptyRule;
import com.ebr163.inspector.sample.R;

public class ValueExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_example);
        final TextView textView = findViewById(R.id.text);
        final Inspector inspector = new Inspector(getLifecycle());

        InspectionValue<String> inspectionValue1 = new InspectValueBuilder<String>(null)
                .addRule(new TextNotEmptyRule("ins1"))
                .addErrorListener(new InspectionValue.OnErrorListener() {
                    @Override
                    public void setErrorEnabled(boolean enabled, String error) {
                        if (!enabled)
                            textView.setText("testNotError" + " " + "ins1");
                        else
                            textView.setText("testError" + " " + error);
                    }
                }).build();

        InspectionValue<String> inspectionValue2 = new InspectValueBuilder<>("")
                .addRule(new TextNotEmptyRule("ins2"))
                .addErrorListener(new InspectionValue.OnErrorListener() {
                    @Override
                    public void setErrorEnabled(boolean enabled, String error) {
                        if (!enabled)
                            textView.append("testNotError" + " " + "ins2");
                        else
                            textView.append("testError" + " " + error);
                    }
                }).build();

        InspectionValue<String> inspectionValue3 = new InspectValueBuilder<>("ss")
                .addRule(new TextNotEmptyRule("ins2"))
                .build();

        inspector.addInspection(inspectionValue1);
        inspector.addInspection(inspectionValue2);
        inspector.addInspection(inspectionValue3);

        findViewById(R.id.check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("result", String.valueOf(inspector.inspect()));
            }
        });
    }
}
