package org.example.data

import org.example.domain.Company
import org.example.domain.Repository

class RepositoryImpl : Repository {

    private val outList: List<Company> = listOf(
        Company("Sber", "Banking", emptyList(), "89191234567"),
        Company("Yandex", "IT", emptyList(), "88005353535"),
        Company("VK", "Public services", emptyList(), "88005362637"),
        Company("Tinkoff", "Banking", emptyList(), "89001237654")
    )

    override fun getCompanyList(): List<Company> {
        return outList
    }

    override fun getCompanyById(id: Int): Company? {
        return outList.getOrNull(id)
    }
}

