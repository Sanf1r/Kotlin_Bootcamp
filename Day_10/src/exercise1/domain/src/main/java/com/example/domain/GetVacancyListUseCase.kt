package com.example.domain

class GetVacancyListUseCase(private val repository: Repository) {
    suspend fun execute(): List<VacancyInfo> {
        return repository.getVacancyList()
    }
}