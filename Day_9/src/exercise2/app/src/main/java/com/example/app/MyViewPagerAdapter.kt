package com.example.app

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.app.fragments.CompaniesFragment
import com.example.app.fragments.FragmentData
import com.example.app.fragments.VacanciesFragment

class MyViewPagerAdapter(
    fragment: Fragment,
    private val fragmentDataList: List<FragmentData>,
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return fragmentDataList.size
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) CompaniesFragment() else VacanciesFragment()
    }
}