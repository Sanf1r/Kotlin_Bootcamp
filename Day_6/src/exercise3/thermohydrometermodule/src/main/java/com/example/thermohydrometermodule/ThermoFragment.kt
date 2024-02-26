package com.example.thermohydrometermodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.loggermodule.FragmentWithLogger
import com.example.loggermodule.LoggerModule
import com.example.loggermodule.logger
import com.example.thermohydrometermodule.databinding.FragmentThermoBinding

class ThermoFragment : FragmentWithLogger(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentThermoBinding
    private var mode = Mode.CELSIUS
    private var season = Season.SUMMER
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThermoBinding.inflate(layoutInflater, container, false)
        LoggerModule.i(tagName, "Fragment view creation started")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoggerModule.i(tagName, "Fragment view created")

        binding.tempInput.logger("Temperature")
        binding.humInput.logger("Humidity")

        binding.modeSpinner.onItemSelectedListener = this
        binding.seasonSpinner.onItemSelectedListener = this
        binding.calculate.setOnClickListener {
            LoggerModule.i(tagName, "Calculate button clicked ${++count} times")
            binding.resultText.text = Thermohydrometer.thermoLogic(
                mode, season,
                binding.tempInput.text.toString().toDoubleOrNull(),
                binding.humInput.text.toString().toIntOrNull()
            )
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            when (parent.id) {
                R.id.mode_spinner -> {
                    LoggerModule.i("ModeTag", "Output mode changed to " + binding.modeSpinner.getItemAtPosition(position))
                    mode = Mode.entries[position]
                }

                R.id.season_spinner -> {
                    LoggerModule.i("SeasonTag", "Season changed to " + binding.seasonSpinner.getItemAtPosition(position))
                    season = Season.entries[position]
                }
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}