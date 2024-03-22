package com.example.exercise1

import retrofit2.http.GET
import retrofit2.http.Path

interface RetroCompany {
//    @GET("companies/{id}")
//    suspend fun getCompanyById(@Path("id") id: Int) : Company?

    @GET("companies")
    suspend fun getCompanies() : List<CompanyInfo>
}