package com.example.loggermodule

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.logger(name: String) {
    val textWatcher = object : TextWatcher {
        private var previousText: String? = null

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            previousText = s.toString()
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            LoggerModule.i(name, "Previous value: $previousText, New value: $s") }

        override fun afterTextChanged(s: Editable?) {}
    }
    this.addTextChangedListener(textWatcher)
}