package org.example.domain

class GetCompanyListUseCase(private val repository: Repository) {

    fun execute(): List<CompanyInfo> {
        val tmp = repository.getCompanyList()
        val res = mutableListOf<CompanyInfo>()
        tmp.forEach { res.add(CompanyInfo(it.name, it.fieldOfActivity)) }
        return res
    }
}