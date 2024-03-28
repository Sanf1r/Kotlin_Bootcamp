package com.example.app.recyclerViewUtils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.domain.VacancyInfo

class VacancyInfoAdapter(
    private val items: List<VacancyInfo>,
    private val listener: RecyclerViewEvent,
) :
    RecyclerView.Adapter<VacancyInfoAdapter.VacancyInfoViewHolder>() {

    inner class VacancyInfoViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val vacancyProfession: TextView = view.findViewById(R.id.vacancy_info_profession)
        val vacancyLevel: TextView = view.findViewById(R.id.vacancy_info_level)
        val vacancySalary: TextView = view.findViewById(R.id.vacancy_info_salary)
        val vacancyCompany: TextView = view.findViewById(R.id.vacancy_info_company)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vacancy_info, parent, false)
        return VacancyInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacancyInfoViewHolder, position: Int) {
        val vacancy = items[position]
        holder.vacancyProfession.text =
            holder.itemView.context.getString(R.string.profession_text, vacancy.profession)
        holder.vacancyLevel.text =
            holder.itemView.context.getString(R.string.level_text, vacancy.level)
        holder.vacancySalary.text =
            holder.itemView.context.getString(R.string.salary_text, vacancy.salary)
        holder.vacancyCompany.text =
            holder.itemView.context.getString(R.string.name_text, vacancy.companyName)
    }

    override fun getItemCount() = items.size
}