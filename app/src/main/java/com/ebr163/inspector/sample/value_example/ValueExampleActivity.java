package com.ebr163.inspector.sample.value_example;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ebr163.inspector.Inspection;
import com.ebr163.inspector.Inspector;
import com.ebr163.inspector.rule.TextNotEmptyRule;
import com.ebr163.inspector.sample.R;
import com.ebr163.inspector.variable.InspectVariableBuilder;

import androidx.appcompat.app.AppCompatActivity;

public class ValueExampleActivity extends AppCompatActivity {

    private Inspector inspector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_example);
        final TextView textView = findViewById(R.id.text);
        inspector = new Inspector(getLifecycle());

        Inspection<String> inspectionVariable1 = new InspectVariableBuilder<String>(null)
                .addRule(new TextNotEmptyRule("ins1"))
                .addErrorListener((enabled, error) -> {
                    if (!enabled)
                        textView.setText("value1" + " " + "success");
                    else
                        textView.setText("value1" + " " + error);
                }).build();

        Inspection<String> inspectionVariable2 = new InspectVariableBuilder<>("")
                .addRule(new TextNotEmptyRule("ins2"))
                .addErrorListener((enabled, error) -> {
                    if (!enabled)
                        textView.append("value1" + " " + "success");
                    else
                        textView.append("value1" + " " + error);
                }).build();

        Inspection<String> inspectionVariable3 = new InspectVariableBuilder<>("ss")
                .addRule(new TextNotEmptyRule("ins3"))
                .build();

        inspector.addInspection(inspectionVariable1);
        inspector.addInspection(inspectionVariable2);
        inspector.addInspection(inspectionVariable3);

        findViewById(R.id.check).setOnClickListener(view -> Log.d("result", String.valueOf(inspector.inspect())));
    }
}
