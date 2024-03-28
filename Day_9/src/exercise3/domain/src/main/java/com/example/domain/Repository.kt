package com.example.domain

interface Repository {
    suspend fun getCompanyList(): List<CompanyInfo>
    suspend fun getCompanyById(id: Int): Company
    suspend fun getVacancyList(): List<VacancyInfo>
}
