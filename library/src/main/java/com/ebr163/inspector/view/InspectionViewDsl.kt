package com.ebr163.inspector.view

import android.view.View
import com.google.android.material.textfield.TextInputLayout

fun <V : View, Type> inspectionView(init: InspectionViewBuilderDsl<V, Type>.() -> Unit): InspectionView<V, Type> {
    val builder = InspectionViewBuilderDsl<V, Type>()
    builder.init()
    return builder.build()
}

fun inspectionTextInputLayotView(init: InspectionViewBuilderDsl<TextInputLayout, String>.() -> Unit): InspectionView<TextInputLayout, String> {
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