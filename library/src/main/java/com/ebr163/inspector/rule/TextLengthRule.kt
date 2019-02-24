package com.ebr163.inspector.rule

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class TextLengthRule(
        private val length: Int,
        private val textLength: TextLength,
        errorMessage: String
) : AbstractRule<String>(errorMessage) {

    enum class TextLength {
        MORE, LESS, EQUAL, MORE_OR_EQUAL, LESS_OR_EQUAL
    }

    override fun verify(value: String?): Boolean {
        if (value == null) {
            return false
        }

        return when (textLength) {
            TextLength.EQUAL -> value.length == length
            TextLength.LESS -> value.length < length
            TextLength.MORE -> value.length > length
            TextLength.LESS_OR_EQUAL -> value.length <= length
            else -> value.length >= length
        }
    }
}
