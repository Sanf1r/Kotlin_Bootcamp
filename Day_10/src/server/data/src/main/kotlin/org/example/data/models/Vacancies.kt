package org.example.data.models

import kotlinx.serialization.Serializable
import org.example.domain.Vacancy
import org.jetbrains.exposed.sql.*


object Vacancies : Table() {
    val id = integer("id")
    val companyId = integer("companyId").references(Companies.id)
    val profession = varchar("profession", 128)
    val level = varchar("level", 128)
    val salary = integer("salary")
    val description = varchar("description", 128)

    override val primaryKey = PrimaryKey(id)
}