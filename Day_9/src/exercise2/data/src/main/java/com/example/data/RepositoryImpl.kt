package com.example.data

import com.example.domain.CompanyInfo
import com.example.domain.Repository
import retrofit2.Retrofit

class RepositoryImpl(private val retrofit: Retrofit) : Repository {

    override suspend fun getCompanyList(): List<CompanyInfo> {
        val api = retrofit.create(RetrofitApi::class.java)
        return api.getCompanies()
    }
}
