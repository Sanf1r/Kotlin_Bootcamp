package com.example.thermohydrometermodule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.thermohydrometermodule.databinding.FragmentThermoBinding


class ThermoFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentThermoBinding
    private var mode = Mode.CELSIUS
    private var season = Season.SUMMER

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThermoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.modeSpinner.onItemSelectedListener = this
        binding.seasonSpinner.onItemSelectedListener = this
        binding.calculate.setOnClickListener {
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
                R.id.mode_spinner -> mode = Mode.entries[position]
                R.id.season_spinner -> season = Season.entries[position]
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}