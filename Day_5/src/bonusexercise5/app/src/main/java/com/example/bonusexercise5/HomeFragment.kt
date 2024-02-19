package com.example.bonusexercise5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bonusexercise5.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGame.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_gameFragment) }
        binding.btnAbout.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_aboutFragment) }
        binding.btnFreeGame.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_freeFragment) }
        binding.btnSettings.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_settingsFragment) }
    }
}