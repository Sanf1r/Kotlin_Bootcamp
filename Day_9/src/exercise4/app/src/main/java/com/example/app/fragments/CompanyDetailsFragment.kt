package com.example.app.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.App
import com.example.app.recyclerViewUtils.DotsIndicatorDecoration
import com.example.app.recyclerViewUtils.RecyclerViewEvent
import com.example.app.recyclerViewUtils.SnapHelper
import com.example.app.recyclerViewUtils.VacancyAdapter
import com.example.app.databinding.FragmentCompanyDetailsBinding
import com.example.domain.Company
import com.example.domain.GetCompanyByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompanyDetailsFragment : Fragment(), RecyclerViewEvent {
    private lateinit var binding: FragmentCompanyDetailsBinding
    private var product: Company? = null

    private val args: CompanyDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var idUseCase: GetCompanyByIdUseCase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCompanyDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.companyVacanciesOut.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.companyVacanciesOut.addItemDecoration(
            DotsIndicatorDecoration(
                Color.GRAY,
                Color.BLACK
            )
        )
        SnapHelper.attachToRecyclerView(binding.companyVacanciesOut)
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    override fun onItemClick(position: Int) {
        val vacancyId = product?.vacancies?.get(position)?.id
        findNavController().navigate(
            CompanyDetailsFragmentDirections.actionCompanyDetailsFragmentToVacancyDetailsFragment(
                vacancyId!!, args.companyId
            )
        )
    }

    private fun changeUi(product: Company) {
        binding.companyNameOut.text = product.name
        binding.companyFieldOut.text = product.fieldOfActivity
        binding.companyVacanciesOut.adapter = VacancyAdapter(product.vacancies, this)
        binding.companyContactsOut.text = product.contacts
    }

    private fun updateData() = CoroutineScope(Dispatchers.IO).launch {
        product = idUseCase.execute(args.companyId)
        withContext(Dispatchers.Main) {
            changeUi(product!!)
        }
    }
}