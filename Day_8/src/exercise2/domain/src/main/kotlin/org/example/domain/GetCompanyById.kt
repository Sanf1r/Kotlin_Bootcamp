package org.example.domain

class GetCompanyById(private val repository: Repository) {

    fun execute(id: Int): Company? {
        return repository.getCompanyById(id)
    }
}