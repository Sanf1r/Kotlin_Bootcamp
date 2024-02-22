package com.example.exercise1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.exercise1.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.circlesModule.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_circlesFragment) }
        binding.primeNumbersModule.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_primeNumbersFragment) }
        binding.thermohydrometerModule.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_thermoFragment) }
    }

}