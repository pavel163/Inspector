package com.ebr163.inspector.sample.pair;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.ebr163.inspector.Inspection;
import com.ebr163.inspector.Inspector;
import com.ebr163.inspector.pair.InspectPairBuilder;
import com.ebr163.inspector.rule.TextNotEmptyRule;
import com.ebr163.inspector.rule.pair.ConfirmPasswordRule;
import com.ebr163.inspector.sample.R;
import com.ebr163.inspector.view.TextInputLayoutInspectViewBuilder;

public class PairExampleActivity extends AppCompatActivity {

    private Inspector inspector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_example);
        inspector = new Inspector(getLifecycle());
        findViewById(R.id.checkBtn).setOnClickListener(view1 -> inspector.inspect());

        TextInputLayout til1 = findViewById(R.id.til1);
        TextInputLayout til2 = findViewById(R.id.til2);

        Inspection<String> inspectionView1 = new TextInputLayoutInspectViewBuilder(til1)
                .addRule(new TextNotEmptyRule("Поле 1 не должно быть пустым"))
                .build();

        Inspection<String> inspectionView2 = new TextInputLayoutInspectViewBuilder(til2)
                .addRule(new TextNotEmptyRule("Поле 2 не должно быть пустым"))
                .build();

        Inspection<Pair<String, String>> inspectionPair = new InspectPairBuilder<>(inspectionView1, inspectionView2)
                .addRule(new ConfirmPasswordRule("error"))
                .addErrorListener((inspections, enabled, error) -> inspections.second.setErrorEnabled(enabled, error))
                .build();

        inspector.addInspection(inspectionPair);
    }
}
