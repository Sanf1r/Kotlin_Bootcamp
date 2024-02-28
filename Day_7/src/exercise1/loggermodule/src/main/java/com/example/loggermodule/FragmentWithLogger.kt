package com.example.loggermodule

import android.os.Bundle
import androidx.fragment.app.Fragment

open class FragmentWithLogger : Fragment() {
    val tagName = "${this::class.simpleName}Tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoggerModule.i(tagName, "Fragment created")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        LoggerModule.i(tagName, "Fragment view state restored")
    }

    override fun onStart() {
        super.onStart()
        LoggerModule.i(tagName, "Fragment started")
    }

    override fun onResume() {
        super.onResume()
        LoggerModule.i(tagName, "Fragment resumed")
    }

    override fun onPause() {
        super.onPause()
        LoggerModule.i(tagName, "Fragment paused")
    }

    override fun onStop() {
        super.onStop()
        LoggerModule.i(tagName, "Fragment stopped")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LoggerModule.i(tagName, "Fragment saved instance state")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LoggerModule.i(tagName, "Fragment view destroyed")
    }

    override fun onDestroy() {
        super.onDestroy()
        LoggerModule.i(tagName, "Fragment destroyed")
    }
}