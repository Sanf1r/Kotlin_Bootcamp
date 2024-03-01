package com.example.exercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise2.databinding.ActivityMainBinding
import com.example.loggermodule.LoggerModule

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tag = "${this::class.simpleName}Tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        LoggerModule.i(tag, "Activity created")
    }

    override fun onStart() {
        super.onStart()
        LoggerModule.i(tag, "Activity started")
    }

    override fun onResume() {
        super.onResume()
        LoggerModule.i(tag, "Activity resumed")
    }

    override fun onPause() {
        super.onPause()
        LoggerModule.i(tag, "Activity paused")
    }

    override fun onStop() {
        super.onStop()
        LoggerModule.i(tag, "Activity stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        LoggerModule.i(tag, "Activity destroyed")
    }
}