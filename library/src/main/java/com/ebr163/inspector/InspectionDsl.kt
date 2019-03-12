package com.ebr163.inspector

import android.view.View
import com.ebr163.inspector.pair.InspectionPair
import com.ebr163.inspector.pair.InspectionPairBuilderDsl
import com.ebr163.inspector.variable.InspectionVariable
import com.ebr163.inspector.variable.InspectionVariableBuilderDsl
import com.ebr163.inspector.view.InspectionView
import com.ebr163.inspector.view.InspectionViewBuilderDsl
import com.google.android.material.textfield.TextInputLayout


fun <Type> inspectionVariable(init: InspectionVariableBuilderDsl<Type>.() -> Unit): InspectionVariable<Type> {
    val builder = InspectionVariableBuilderDsl<Type>()
    builder.init()
    return builder.build()
}

fun <V : View, Type> inspectionView(init: InspectionViewBuilderDsl<V, Type>.() -> Unit): InspectionView<V, Type> {
    val builder = InspectionViewBuilderDsl<V, Type>()
    builder.init()
    return builder.build()
}

fun <Type1, Type2> inspectionPair(init: InspectionPairBuilderDsl<Type1, Type2>.() -> Unit): InspectionPair<Type1, Type2> {
    val builder = InspectionPairBuilderDsl<Type1, Type2>()
    builder.init()
    return builder.build()
}

fun inspectionTextInputLayoutView(init: InspectionViewBuilderDsl<TextInputLayout, String>.() -> Unit): InspectionView<TextInputLayout, String> {
    val builder = InspectionViewBuilderDsl<TextInputLayout, String>()
    builder.init()
    builder.onValueListener {
        val charSequence = it.editText?.text
        charSequence.toString()
    }
    builder.onErrorListener { view, error, enabled ->
        if (enabled) {
            view?.error = error
            view?.isErrorEnabled = true
        } else {
            view?.isErrorEnabled = false
        }
    }
    return builder.build()
}