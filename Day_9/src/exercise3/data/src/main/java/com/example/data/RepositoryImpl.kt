package com.example.data

import com.example.domain.Company
import com.example.domain.CompanyInfo
import com.example.domain.Repository
import com.example.domain.VacancyInfo
import retrofit2.Retrofit

class RepositoryImpl(private val retrofit: Retrofit) : Repository {

    override suspend fun getCompanyList(): List<CompanyInfo> {
        val api = retrofit.create(RetrofitApi::class.java)
        return api.getCompanies()
    }

    override suspend fun getCompanyById(id: Int): Company {
        val api = retrofit.create(RetrofitApi::class.java)
        return api.getCompanyById(id)
    }

    override suspend fun getVacancyList(): List<VacancyInfo> {
        val api = retrofit.create(RetrofitApi::class.java)
        return api.getVacancies()
    }
}
