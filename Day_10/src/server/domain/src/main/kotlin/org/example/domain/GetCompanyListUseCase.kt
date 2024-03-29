package org.example.domain

class GetCompanyListUseCase(private val repository: Repository) {

    suspend fun execute(): List<CompanyInfo> {
        return repository.getCompanyList().map { CompanyInfo(it.id, it.name, it.fieldOfActivity) }
    }
}