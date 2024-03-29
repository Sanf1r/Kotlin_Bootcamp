package com.example.app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.App
import com.example.app.recyclerViewUtils.RecyclerViewEvent
import com.example.app.recyclerViewUtils.VacancyInfoAdapter
import com.example.app.databinding.FragmentVacanciesBinding
import com.example.domain.GetVacancyListUseCase
import com.example.domain.VacancyInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class VacanciesFragment : Fragment(), RecyclerViewEvent {
    private lateinit var binding: FragmentVacanciesBinding
    private var product: List<VacancyInfo>? = null

    @Inject
    lateinit var vacancyUseCase: GetVacancyListUseCase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVacanciesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun changeUi() {
        val adapter = product?.let { VacancyInfoAdapter(it, this) }
        binding.list.adapter = adapter
    }

    private fun updateData() = CoroutineScope(Dispatchers.IO).launch {
        product = vacancyUseCase.execute()
        withContext(Dispatchers.Main) {
            changeUi()
        }
    }

    override fun onItemClick(position: Int) {
        val vacancyId = product?.get(position)?.id
        val companyId = product?.get(position)?.companyId
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToVacancyDetailsFragment(
                vacancyId!!, companyId!!
            )
        )
    }

}