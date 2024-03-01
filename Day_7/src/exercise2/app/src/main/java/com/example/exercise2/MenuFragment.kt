package com.example.exercise2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.exercise2.databinding.FragmentMenuBinding
import com.example.loggermodule.FragmentWithLogger
import com.example.loggermodule.LoggerModule

class MenuFragment : FragmentWithLogger() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        LoggerModule.i(tagName, "Fragment view creation started")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoggerModule.i(tagName, "Fragment view created")
        binding.circlesModule.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_circlesFragment) }
        binding.primeNumbersModule.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_primeNumbersFragment) }
        binding.thermohydrometerModule.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_thermoFragment) }
        binding.speechModule.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_speechFragment) }
        binding.intensiveModule.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_intensiveFragment) }
    }

}