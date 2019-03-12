package com.ebr163.inspector.sample.view_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ebr163.inspector.Inspector
import com.ebr163.inspector.inspectionTextInputLayoutView
import com.ebr163.inspector.rule.TextLengthRule
import com.ebr163.inspector.rule.TextNotEmptyRule
import com.ebr163.inspector.sample.R
import com.ebr163.inspector.view.TextInputLayoutInspectViewBuilder
import kotlinx.android.synthetic.main.activity_main.*

class ViewExampleActivity : AppCompatActivity() {

    private var inspector = Inspector(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkBtn.setOnClickListener { inspector.inspect() }

        val inspectionView1 = TextInputLayoutInspectViewBuilder(til1)
                .addRule(TextNotEmptyRule(getString(R.string.field_is_empty, 1)))
                .addRule(TextLengthRule(5, TextLengthRule.TextLength.EQUAL, "error"))
                .checkAfterLostFocus(TextNotEmptyRule(""), R.id.ed1)
                .build()

        val inspectionView2 = TextInputLayoutInspectViewBuilder(til2)
                .addRule(TextNotEmptyRule(getString(R.string.field_is_empty, 2)))
                .build()

        val inspectionView3 = TextInputLayoutInspectViewBuilder(til3)
                .addRule(TextNotEmptyRule(getString(R.string.field_is_empty, 3)))
                .build()

        val inspectionView4 = inspectionTextInputLayoutView {
            this.view = til4
            rule(TextNotEmptyRule(getString(R.string.field_is_empty, 4)))
            rule(TextLengthRule(5, TextLengthRule.TextLength.EQUAL, getString(R.string.field_length_incorrect)))
            enabledCheckAfterLostFocus = true
            focusableViewId = R.id.ed4
        }

        inspector.addInspection("key1", inspectionView1)
        inspector.addInspection(inspectionView2)
        inspector.addInspection("key3", inspectionView3)
        inspector.addInspection("key4", inspectionView4)
    }
}