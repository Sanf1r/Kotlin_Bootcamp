package com.example.exercise1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise1.databinding.FragmentCompaniesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CompaniesFragment : Fragment() {
    private lateinit var binding: FragmentCompaniesBinding
    private lateinit var retrofit: Retrofit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCompaniesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        binding.list.layoutManager = LinearLayoutManager(requireContext())
        updateData()
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    fun updateData() = CoroutineScope(Dispatchers.IO).launch {
        println("update launched")
        val api = retrofit.create(RetroCompany::class.java)
        val product = api.getCompanies()
        withContext(Dispatchers.Main){
            val adapter = CompanyInfoAdapter(product)
            binding.list.adapter = adapter
        }
    }




}

data class Company(
    val id: Int,
    val name: String,
    val fieldOfActivity: String,
    val vacancies: List<Vacancy>,
    val contacts: String,
)

data class Vacancy(
    val id: Int,
    val profession: String,
    val level: String,
    val salary: Int,
    val description: String,
)

data class CompanyInfo(
    val id: Int,
    val name: String,
    val fieldOfActivity: String,
)

class CompanyInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val companyId: TextView = view.findViewById(R.id.company_info_id)
    val companyName: TextView = view.findViewById(R.id.company_info_name)
    val companyField: TextView = view.findViewById(R.id.company_info_field)
}

class CompanyInfoAdapter(private val items: List<CompanyInfo>) :
    RecyclerView.Adapter<CompanyInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.company_info_layout, parent, false)
        return CompanyInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyInfoViewHolder, position: Int) {
        val company = items[position]
        holder.companyId.text = "Company id: " + company.id.toString()
        holder.companyName.text = "Company name: " + company.name
        holder.companyField.text = "Company field of activity: " + company.fieldOfActivity
    }

    override fun getItemCount() = items.size
}