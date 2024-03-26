package com.example.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app.databinding.FragmentVacanciesBinding


class VacanciesFragment : Fragment() {
    private lateinit var binding: FragmentVacanciesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVacanciesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}