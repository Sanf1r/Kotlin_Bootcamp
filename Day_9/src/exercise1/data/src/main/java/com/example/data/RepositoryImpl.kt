package com.example.data

import com.example.domain.Company
import com.example.domain.CompanyInfo
import com.example.domain.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl : Repository{
    private lateinit var retrofit: Retrofit

    override suspend fun getCompanyList(): List<CompanyInfo> {
        retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(RetrofitApi::class.java)
        return api.getCompanies()
    }
}
