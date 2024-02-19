package com.example.bonusexercise5

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.bonusexercise5.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val keySound = "mute"
    private val keyDelay = "delay"
    private val keyLight = "light"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        pref = requireContext().getSharedPreferences("Storage", AppCompatActivity.MODE_PRIVATE)
        editor = pref.edit()
        val soundFlag = pref.getBoolean(keySound, false)
        binding.soundSwitch.isChecked = soundFlag
        binding.soundSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor.putBoolean(keySound, true)
            } else {
                editor.putBoolean(keySound, false)
            }
            editor.apply()
        }
        val delayInit = pref.getLong(keyDelay, 100)
        binding.slider.value = delayInit.toFloat()
        binding.slider.addOnChangeListener { _, value, _ ->
            editor.putLong(keyDelay,value.toLong()).apply()
        }
        val highlightFlag = pref.getBoolean(keyLight, false)
        binding.highlightSwitch.isChecked = highlightFlag
        binding.highlightSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor.putBoolean(keyLight, true)
            } else {
                editor.putBoolean(keyLight, false)
            }
            editor.apply()
        }
    }

}