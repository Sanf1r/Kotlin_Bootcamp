package com.example.app.recyclerViewUtils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.domain.Vacancy

class VacancyAdapter(
    private val items: List<Vacancy>,
    private val listener: RecyclerViewEvent,
) :
    RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {

    inner class VacancyViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val vacancyProfession: TextView = view.findViewById(R.id.vacancy_profession)
        val vacancyLevel: TextView = view.findViewById(R.id.vacancy_level)
        val vacancySalary: TextView = view.findViewById(R.id.vacancy_salary)
        val vacancyDescription: TextView = view.findViewById(R.id.vacancy_description)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vacancy, parent, false)
        return VacancyViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = items[position]
        holder.vacancyProfession.text =
            holder.itemView.context.getString(R.string.profession_text, vacancy.profession)
        holder.vacancyLevel.text =
            holder.itemView.context.getString(R.string.level_text, vacancy.level)
        holder.vacancySalary.text =
            holder.itemView.context.getString(R.string.salary_text, vacancy.salary)
        holder.vacancyDescription.text =
            holder.itemView.context.getString(R.string.description_text, vacancy.description)
    }

    override fun getItemCount() = items.size
}