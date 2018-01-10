package com.ebr163.inspector.sample.value_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ebr163.inspector.Inspector;
import com.ebr163.inspector.rule.TextNotEmptyRule;
import com.ebr163.inspector.sample.R;
import com.ebr163.inspector.value.InspectValueBuilder;
import com.ebr163.inspector.value.InspectionValue;

public class ValueExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_example);
        final TextView textView = findViewById(R.id.text);
        final Inspector inspector = new Inspector(getLifecycle());

        InspectionValue<String> inspectionValue1 = new InspectValueBuilder<String>(null)
                .addRule(new TextNotEmptyRule("ins1"))
                .addErrorListener((enabled, error) -> {
                    if (!enabled)
                        textView.setText("value1" + " " + "success");
                    else
                        textView.setText("value1" + " " + error);
                }).build();

        InspectionValue<String> inspectionValue2 = new InspectValueBuilder<>("")
                .addRule(new TextNotEmptyRule("ins2"))
                .addErrorListener((enabled, error) -> {
                    if (!enabled)
                        textView.append("value1" + " " + "success");
                    else
                        textView.append("value1" + " " + error);
                }).build();

        InspectionValue<String> inspectionValue3 = new InspectValueBuilder<>("ss")
                .addRule(new TextNotEmptyRule("ins3"))
                .build();

        inspector.addInspection(inspectionValue1);
        inspector.addInspection(inspectionValue2);
        inspector.addInspection(inspectionValue3);

        findViewById(R.id.check).setOnClickListener(view -> Log.d("result", String.valueOf(inspector.inspect())));
    }
}
