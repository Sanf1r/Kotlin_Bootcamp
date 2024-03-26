package com.example.app

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyViewPagerAdapter(
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