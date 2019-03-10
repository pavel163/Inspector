package com.ebr163.inspector.sample.view_example

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ebr163.inspector.Inspection
import com.ebr163.inspector.Inspector
import com.ebr163.inspector.rule.TextLengthRule
import com.ebr163.inspector.rule.TextNotEmptyRule
import com.ebr163.inspector.sample.R
import com.ebr163.inspector.view.TextInputLayoutInspectViewBuilder
import com.google.android.material.textfield.TextInputLayout
import androidx.fragment.app.Fragment
import com.ebr163.inspector.view.inspectionTextInputLayotView

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class ViewExampleFragment : Fragment() {

    private var inspector: Inspector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        inspector = Inspector(this.lifecycle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_base_example, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inspector?.setInspectListener { aBoolean, stringMap ->
            Log.d("inspector", "values")
            null
        }

        getView()!!.findViewById<View>(R.id.checkBtn).setOnClickListener { inspector?.inspect() }

        val til1 = getView()!!.findViewById<TextInputLayout>(R.id.til1)
        val til2 = getView()!!.findViewById<TextInputLayout>(R.id.til2)
        val til3 = getView()!!.findViewById<TextInputLayout>(R.id.til3)
        val til4 = getView()!!.findViewById<TextInputLayout>(R.id.til4)

        val inspectionView1 = TextInputLayoutInspectViewBuilder(til1)
                .addRule(TextNotEmptyRule("Поле 1 не должно быть пустым"))
                .addRule(TextLengthRule(5, TextLengthRule.TextLength.EQUAL, "error"))
                .checkAfterLostFocus(TextNotEmptyRule(""), R.id.ed1)
                .build()

        val inspectionView2 = TextInputLayoutInspectViewBuilder(til2)
                .addRule(TextNotEmptyRule("Поле 2 не должно быть пустым"))
                .build()

        val inspectionView3 = TextInputLayoutInspectViewBuilder(til3)
                .addRule(TextNotEmptyRule("Поле 3 не должно быть пустым"))
                .build()

        val inspectionView4 = inspectionTextInputLayotView {
            this.view = til4
            rule(TextNotEmptyRule("Поле 4 не должно быть пустым"))
            rule(TextLengthRule(5, TextLengthRule.TextLength.EQUAL, "Length is incorrect"))
            enabledCheckAfterLostFocus = true
            focusableViewId = R.id.ed4
        }

        inspector?.addInspection("key1", inspectionView1)
        inspector?.addInspection(inspectionView2)
        inspector?.addInspection("key3", inspectionView3)
        inspector?.addInspection("key4", inspectionView4)
    }

    override fun onDestroyView() {
        inspector?.clear()
        super.onDestroyView()
    }
}
