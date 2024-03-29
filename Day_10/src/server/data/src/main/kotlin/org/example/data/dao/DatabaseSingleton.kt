package org.example.data.dao

import kotlinx.coroutines.Dispatchers
import org.example.data.models.Companies
import org.example.data.models.Vacancies
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

private operator fun <E> List<E>.component6() = this[5]

object DatabaseSingleton {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;"
        val database = Database.connect(jdbcURL, driverClassName)
        transaction(database) {
            SchemaUtils.drop(Companies, Vacancies)
            SchemaUtils.create(Companies, Vacancies)
        }
        fillCompanies(database)
        fillVacancies(database)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    private fun fillCompanies(database: Database) {
        object {}.javaClass.getResourceAsStream("/CompanyData.csv")?.bufferedReader()?.forEachLine { line ->
            val (id, name, fieldOfActivity, contacts) = line.split(",")
            transaction(database) {
                Companies.insert {
                    it[Companies.id] = id.toInt()
                    it[Companies.name] = name
                    it[Companies.fieldOfActivity] = fieldOfActivity
                    it[Companies.contacts] = contacts
                }
            }
        }
    }

    private fun fillVacancies(database: Database) {
        object {}.javaClass.getResourceAsStream("/VacancyData.csv")?.bufferedReader()?.forEachLine { line ->
            val (id, companyId, profession, level, salary, description) = line.split(",")
            transaction(database) {
                Vacancies.insert {
                    it[Vacancies.id] = id.toInt()
                    it[Vacancies.companyId] = companyId.toInt()
                    it[Vacancies.profession] = profession
                    it[Vacancies.level] = level
                    it[Vacancies.salary] = salary.toInt()
                    it[Vacancies.description] = description
                }
            }
        }
    }
}