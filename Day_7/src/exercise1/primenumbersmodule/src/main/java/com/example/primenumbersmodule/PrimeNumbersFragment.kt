package com.example.primenumbersmodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.lifecycleScope
import com.example.loggermodule.FragmentWithLogger
import com.example.loggermodule.LoggerModule
import com.example.loggermodule.logger
import com.example.primenumbersmodule.databinding.FragmentPrimeNumbersBinding
import kotlinx.coroutines.launch

class PrimeNumbersFragment : FragmentWithLogger(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentPrimeNumbersBinding
    private var mode = false
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrimeNumbersBinding.inflate(layoutInflater, container, false)
        LoggerModule.i(tagName, "Fragment view creation started")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoggerModule.i(tagName, "Fragment view created")

        binding.numberInput.logger("NumberInput")

        binding.orderSpinner.onItemSelectedListener = this
        binding.calculate.setOnClickListener {
            LoggerModule.i(tagName, "Calculate button clicked ${++count} times")
            viewLifecycleOwner.lifecycleScope.launch {
                LoggerModule.i(tagName, "$this started")
                binding.resultText.text = primeNumbersLogic(
                    binding.numberInput.text.toString().toLongOrNull(),
                    mode
                )
                LoggerModule.i(tagName, "$this ended")
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        LoggerModule.i(
            "OrderTag",
            "Grouping order changed to " + binding.orderSpinner.getItemAtPosition(position)
        )
        mode = position == 0
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}