package com.ebr163.inspector.view


import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class TextInputLayoutInspectViewBuilder(v: TextInputLayout) : InspectViewBuilder<TextInputLayout, String>(v) {

    init {
        addValueListener {
            val charSequence = it.editText!!.text
            charSequence.toString()
        }

        addErrorListener { view, error, enabled ->
            if (enabled) {
                view?.error = error
                view?.isErrorEnabled = true
            } else {
                view?.isErrorEnabled = false
            }
        }
    }
}
