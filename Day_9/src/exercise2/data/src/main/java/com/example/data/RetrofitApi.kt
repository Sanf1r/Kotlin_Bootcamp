package com.example.data

import com.example.domain.Company
import com.example.domain.CompanyInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {
    @GET("companies")
    suspend fun getCompanies(): List<CompanyInfo>

    @GET("companies/{id}")
    suspend fun getCompanyById(@Path("id") id: Int): Company
}