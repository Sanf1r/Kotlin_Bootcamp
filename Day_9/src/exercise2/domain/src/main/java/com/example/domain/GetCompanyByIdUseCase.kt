package com.example.domain

class GetCompanyByIdUseCase(private val repository: Repository) {

    suspend fun execute(id: Int): Company {
        return repository.getCompanyById(id)
    }
}