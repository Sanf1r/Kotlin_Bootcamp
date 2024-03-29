package org.example.domain

interface Repository {
    suspend fun getCompanyList(): List<Company>
    suspend fun getCompanyById(id: Int): Company?
    suspend fun getVacancyList(): List<Vacancy>
    suspend fun getVacancyById(id: Int): Vacancy?
}