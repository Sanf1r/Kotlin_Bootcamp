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
import com.example.app.CompanyInfoAdapter
import com.example.app.RecyclerViewEvent
import com.example.app.databinding.FragmentCompaniesBinding
import com.example.domain.CompanyInfo
import com.example.domain.GetCompanyListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CompaniesFragment : Fragment(), RecyclerViewEvent {
    private lateinit var binding: FragmentCompaniesBinding
    private var product: List<CompanyInfo>? = null

    @Inject
    lateinit var companyUseCase: GetCompanyListUseCase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCompaniesBinding.inflate(layoutInflater, container, false)
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

    override fun onItemClick(position: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToCompanyDetailsFragment(
                position
            )
        )
    }

    private fun changeUi() {
        val adapter = product?.let { CompanyInfoAdapter(it, this) }
        binding.list.adapter = adapter
    }

    private fun updateData() = CoroutineScope(Dispatchers.IO).launch {
        product = companyUseCase.execute()
        withContext(Dispatchers.Main) {
            changeUi()
        }
    }
}
