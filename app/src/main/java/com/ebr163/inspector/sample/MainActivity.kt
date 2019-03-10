package com.ebr163.inspector.sample

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.ebr163.inspector.Inspector
import com.ebr163.inspector.rule.TextNotEmptyRule
import com.ebr163.inspector.variable.InspectionVariable
import com.ebr163.inspector.variable.inspectionVariable
import com.ebr163.inspector.view.InspectViewBuilder

class MainActivity : AppCompatActivity() {

    private var inspector: Inspector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inspector = Inspector(this.lifecycle)

        InspectViewBuilder<EditText, String>(EditText(this))
                .addRule(TextNotEmptyRule("text is empty"))
                .addValueListener {
                    it.text.toString()
                }
                .addErrorListener { _, _, _ ->
                    //do something
                }
                .build()

        val inspection: InspectionVariable<String> = inspectionVariable {
            value = "Test"
            rule(TextNotEmptyRule(""))
            onErrorListener { enabled, errorMessage ->
                // do something
            }
        }
        inspector?.addInspection(inspection)
    }

    override fun onDestroy() {
        inspector?.clear()
        super.onDestroy()
    }
}
