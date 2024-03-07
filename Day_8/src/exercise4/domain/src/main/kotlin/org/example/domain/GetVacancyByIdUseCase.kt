package org.example.domain

class GetVacancyByIdUseCase(private val repository: Repository) {

    fun execute(id: Int): Vacancy? {
        val companyList = repository.getCompanyList()
        return companyList.flatMap { it.vacancies }.getOrNull(id)
    }
}