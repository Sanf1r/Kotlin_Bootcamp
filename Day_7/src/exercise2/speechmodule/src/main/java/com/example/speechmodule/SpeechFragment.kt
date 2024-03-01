package com.example.speechmodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.loggermodule.FragmentWithLogger
import com.example.loggermodule.LoggerModule
import com.example.loggermodule.logger
import com.example.speechmodule.databinding.FragmentSpeechBinding
import kotlinx.coroutines.launch

class SpeechFragment : FragmentWithLogger() {
    private lateinit var binding: FragmentSpeechBinding
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpeechBinding.inflate(layoutInflater, container, false)
        LoggerModule.i(tagName, "Fragment view creation started")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoggerModule.i(tagName, "Fragment view created")

        binding.numberInput.logger("NumberInput")

        binding.transform.setOnClickListener {
            LoggerModule.i(tagName, "Transform button clicked ${++count} times")
            viewLifecycleOwner.lifecycleScope.launch {
                LoggerModule.i(tagName, "$this started")
                binding.resultText.text =
                    SpeechModule.speechModule(binding.numberInput.text.toString().toIntOrNull())
                LoggerModule.i(tagName, "$this ended")
            }

        }
    }
}