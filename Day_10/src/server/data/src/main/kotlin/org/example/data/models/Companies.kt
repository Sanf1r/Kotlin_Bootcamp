package org.example.data.models

import kotlinx.serialization.Serializable
import org.example.domain.Vacancy
import org.jetbrains.exposed.sql.*

object Companies : Table() {
    val id = integer("id")
    val name = varchar("name", 128)
    val fieldOfActivity = varchar("fieldIfActivity", 128)
    val contacts = varchar("contacts", 128)

    override val primaryKey = PrimaryKey(id)
}