package com.example.loggermodule

import android.util.Log

object LoggerModule {
    fun i(tag: String, message: String) {
        Log.i(tag, message)
    }
}