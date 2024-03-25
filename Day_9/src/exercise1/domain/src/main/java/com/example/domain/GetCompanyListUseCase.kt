package com.example.domain

class GetCompanyListUseCase(private val repository: Repository) {
    suspend fun execute(): List<CompanyInfo> {
        return repository.getCompanyList()
    }
}
