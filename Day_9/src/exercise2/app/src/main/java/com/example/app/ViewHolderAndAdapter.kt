package com.example.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.CompanyInfo


class CompanyInfoAdapter(
    private val items: List<CompanyInfo>,
    private val listener: RecyclerViewEvent,
) :
    RecyclerView.Adapter<CompanyInfoAdapter.CompanyInfoViewHolder>() {

    inner class CompanyInfoViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val companyName: TextView = view.findViewById(R.id.company_info_name)
        val companyField: TextView = view.findViewById(R.id.company_info_field)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.company_info_layout, parent, false)
        return CompanyInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyInfoViewHolder, position: Int) {
        val company = items[position]
        holder.companyName.text =
            holder.itemView.context.getString(R.string.name_text, company.name)
        holder.companyField.text =
            holder.itemView.context.getString(R.string.field_text, company.fieldOfActivity)
    }

    override fun getItemCount() = items.size
}