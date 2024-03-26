package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

data class FragmentData(val title: String)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
