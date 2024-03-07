package org.example.domain

class GetCompanyListUseCase(private val repository: Repository) {

    fun execute(): List<String> {
        val tmp = repository.getCompanyList()
        val res = mutableListOf<String>()
        tmp.forEach { res.add("Company name: ${it.name}, Field Of Activity: ${it.fieldOfActivity}") }
        return res
    }
}