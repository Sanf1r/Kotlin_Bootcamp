package com.example.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app.MyViewPagerAdapter
import com.example.app.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

data class FragmentData(val title: String)

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val fragmentDataList = listOf(
            FragmentData("Companies"),
            FragmentData("Vacancies")
        )

        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = fragmentDataList[position].title
            }

        binding.viewPager.adapter = MyViewPagerAdapter(this, fragmentDataList)
        tabLayoutMediator.attach()
    }
}