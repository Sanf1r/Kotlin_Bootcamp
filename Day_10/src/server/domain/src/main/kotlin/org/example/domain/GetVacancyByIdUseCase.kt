package org.example.domain

class GetVacancyByIdUseCase(private val repository: Repository) {

    suspend fun execute(id: Int): Vacancy? {
        return repository.getVacancyById(id)
    }
}