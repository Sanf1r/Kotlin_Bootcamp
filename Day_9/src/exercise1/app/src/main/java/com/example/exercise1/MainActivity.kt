package com.example.exercise1

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exercise1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

data class FragmentData(val title: String)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.inject(this)

        val fragmentDataList = listOf(
            FragmentData("Companies"),
            FragmentData("Vacancies")
        )

        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = fragmentDataList[position].title
            }

        binding.viewPager.adapter = MyAdapter(this, fragmentDataList)
        tabLayoutMediator.attach()
    }
}

class MyAdapter(
    activity: AppCompatActivity,
    private val fragmentDataList: List<FragmentData>,
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return fragmentDataList.size
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) CompaniesFragment() else VacanciesFragment()
    }
}
