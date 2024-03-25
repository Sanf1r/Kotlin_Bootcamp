package com.example.domain

interface Repository {
    suspend fun getCompanyList(): List<CompanyInfo>
}
