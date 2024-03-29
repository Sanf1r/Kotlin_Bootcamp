package com.example.data

import com.example.domain.Company
import com.example.domain.CompanyInfo
import com.example.domain.Repository
import com.example.domain.Vacancy
import com.example.domain.VacancyInfo

class RepositoryImpl(private val api: RetrofitApi) : Repository {

    override suspend fun getCompanyList(): List<CompanyInfo> {
        return api.getCompanies()
    }

    override suspend fun getCompanyById(id: Int): Company {
        return api.getCompanyById(id)
    }

    override suspend fun getVacancyList(): List<VacancyInfo> {
        return api.getVacancies()
    }

    override suspend fun getVacancyById(id: Int): Vacancy {
        return api.getVacancyById(id)
    }
}
