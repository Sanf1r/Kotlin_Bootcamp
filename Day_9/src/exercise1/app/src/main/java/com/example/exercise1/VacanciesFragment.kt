package com.example.exercise1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise1.databinding.FragmentVacanciesBinding


class VacanciesFragment : Fragment() {
    lateinit var binding: FragmentVacanciesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVacanciesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}