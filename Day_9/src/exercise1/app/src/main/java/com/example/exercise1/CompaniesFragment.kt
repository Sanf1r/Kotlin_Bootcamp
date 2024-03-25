package com.example.exercise1

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.RepositoryImpl
import com.example.domain.CompanyInfo
import com.example.domain.GetCompanyListUseCase
import com.example.exercise1.databinding.FragmentCompaniesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CompaniesFragment : Fragment() {
    private lateinit var binding: FragmentCompaniesBinding

    @Inject
    lateinit var out: GetCompanyListUseCase

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
        updateData()
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    fun updateData() = CoroutineScope(Dispatchers.IO).launch {
//        println("update launched")
//        val repo = RepositoryImpl()
//        val out = GetCompanyListUseCase(repo)
        val product = out.execute()
        withContext(Dispatchers.Main) {
            val adapter = CompanyInfoAdapter(product)
            binding.list.adapter = adapter
        }
    }


}

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
        holder.companyId.text = holder.itemView.context.getString(R.string.id_text, company.id)
        holder.companyName.text =
            holder.itemView.context.getString(R.string.name_text, company.name)
        holder.companyField.text =
            holder.itemView.context.getString(R.string.field_text, company.fieldOfActivity)
    }

    override fun getItemCount() = items.size
}