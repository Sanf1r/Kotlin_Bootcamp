package org.example.data

import org.example.data.dao.DatabaseSingleton
import org.example.data.models.Companies
import org.example.data.models.Vacancies
import org.example.domain.Company
import org.example.domain.Repository
import org.example.domain.Vacancy
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class RepositoryImpl : Repository {

    override suspend fun getCompanyList(): List<Company> = DatabaseSingleton.dbQuery {
        Companies.selectAll().map(::resultRowToCompany)
    }

    override suspend fun getCompanyById(id: Int) = DatabaseSingleton.dbQuery {
        Companies.select { Companies.id eq id }
            .map(::resultRowToCompany)
            .singleOrNull()
    }

    override suspend fun getVacancyList() = DatabaseSingleton.dbQuery {
        Vacancies.selectAll().map(::resultRowToVacancy)
    }

    override suspend fun getVacancyById(id: Int) = DatabaseSingleton.dbQuery {
        Vacancies.select { Vacancies.id eq id }
            .map(::resultRowToVacancy)
            .singleOrNull()
    }

    private fun resultRowToCompany(row: ResultRow) = Company(
        id = row[Companies.id],
        name = row[Companies.name],
        fieldOfActivity = row[Companies.fieldOfActivity],
        Vacancies.select { Vacancies.companyId eq row[Companies.id] }.map(::resultRowToVacancy),
        contacts = row[Companies.contacts],
    )

    private fun resultRowToVacancy(row: ResultRow) = Vacancy(
        id = row[Vacancies.id],
        companyId = row[Vacancies.companyId],
        profession = row[Vacancies.profession],
        level = row[Vacancies.level],
        salary = row[Vacancies.salary],
        description = row[Vacancies.description],
    )
}

