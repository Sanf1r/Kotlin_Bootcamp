package org.example.domain

class GetVacanciesListUseCase(private val repository: Repository) {

    suspend fun execute(): List<VacancyInfo> = repository.getVacancyList()
        .map { VacancyInfo(it.id, it.profession, it.level, it.salary, it.description, it.companyId) }
}