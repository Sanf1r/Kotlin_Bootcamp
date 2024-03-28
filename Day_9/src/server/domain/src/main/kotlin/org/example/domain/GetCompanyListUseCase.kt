package org.example.domain

class GetCompanyListUseCase(private val repository: Repository) {

    fun execute(): List<CompanyInfo> {
        val tmp = repository.getCompanyList()
        val res = mutableListOf<CompanyInfo>()
        var counter = 1
        tmp.forEach { res.add(CompanyInfo(counter++, it.name, it.fieldOfActivity)) }
        return res
    }
}