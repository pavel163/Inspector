package com.ebr163.inspector.sample.pair_example

import android.os.Bundle

import com.ebr163.inspector.Inspector
import com.ebr163.inspector.rule.TextNotEmptyRule
import com.ebr163.inspector.rule.pair.ConfirmPasswordRule
import com.ebr163.inspector.sample.R
import com.ebr163.inspector.view.TextInputLayoutInspectViewBuilder
import androidx.appcompat.app.AppCompatActivity
import com.ebr163.inspector.inspectionPair
import com.ebr163.inspector.pair.InspectionPair
import kotlinx.android.synthetic.main.activity_pair_example.*

class PairExampleActivity : AppCompatActivity() {

    private val inspector = Inspector(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pair_example)
        checkBtn.setOnClickListener { inspector.inspect() }

        val inspectionView1 = TextInputLayoutInspectViewBuilder(til1)
                .addRule(TextNotEmptyRule(getString(R.string.field_is_empty, 1)))
                .build()

        val inspectionView2 = TextInputLayoutInspectViewBuilder(til2)
                .addRule(TextNotEmptyRule(getString(R.string.field_is_empty, 2)))
                .build()

        val inspectionPair: InspectionPair<String, String> = inspectionPair {
            inspections1 = inspectionView1
            inspections2 = inspectionView2
            rule(ConfirmPasswordRule(getString(R.string.confirm_error, 1, 2)))
            onErrorListener { value, showError, errorMessage ->
                value.second.setErrorEnabled(showError, errorMessage)
            }
        }

        inspector.addInspection(inspectionPair)
    }
}
