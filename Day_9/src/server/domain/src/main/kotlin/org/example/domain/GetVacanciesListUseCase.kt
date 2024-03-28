package org.example.domain

class GetVacanciesListUseCase(private val repository: Repository) {

    fun execute(): List<VacancyInfo> {
        val companyList = repository.getCompanyList()
        val res = mutableListOf<VacancyInfo>()
        var counter = 1
        companyList.forEach {
            if (it.vacancies.isNotEmpty()) {
                it.vacancies.forEach { it2 ->
                    res.add(VacancyInfo(counter++, it2.profession, it2.level, it2.salary, it.name, it.id))
                }
            }
        }
        return res
    }
}