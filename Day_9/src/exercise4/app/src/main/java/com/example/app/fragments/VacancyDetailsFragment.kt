package com.example.app.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.app.App
import com.example.app.databinding.FragmentVacancyDetailsBinding
import com.example.domain.GetVacancyByIdUseCase
import com.example.domain.Vacancy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VacancyDetailsFragment : Fragment() {
    private lateinit var binding: FragmentVacancyDetailsBinding

    private val args: VacancyDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var idUseCase: GetVacancyByIdUseCase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVacancyDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        updateData()

        binding.companyButton.setOnClickListener {
            findNavController().navigate(
                VacancyDetailsFragmentDirections.actionVacancyDetailsFragmentToCompanyDetailsFragment(
                    args.companyId
                )
            )
        }
    }

    private fun changeUi(product: Vacancy) {
        binding.professionNameOut.text = product.profession
        binding.levelOut.text = product.level
        binding.salaryOut.text = product.salary.toString()
        binding.descriptionOut.text = product.description
    }

    private fun updateData() = CoroutineScope(Dispatchers.IO).launch {
        val product = idUseCase.execute(args.id)
        withContext(Dispatchers.Main) {
            changeUi(product)
        }
    }

}