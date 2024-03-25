package com.example.data

import com.example.domain.CompanyInfo
import retrofit2.http.GET

interface RetrofitApi {
    @GET("companies")
    suspend fun getCompanies() : List<CompanyInfo>
}