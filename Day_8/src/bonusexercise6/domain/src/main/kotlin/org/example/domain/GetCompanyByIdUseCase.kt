package org.example.domain

class GetCompanyByIdUseCase(private val repository: Repository) {

    fun execute(id: Int): Company? {
        return repository.getCompanyById(id)
    }
}