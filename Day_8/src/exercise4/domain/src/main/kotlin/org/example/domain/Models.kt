package org.example.domain

import kotlinx.serialization.*

@Serializable
data class Company(
    val id : Int,
    val name: String,
    val fieldOfActivity: String,
    val vacancies: List<Vacancy>,
    val contacts: String
)

@Serializable
data class CompanyInfo(
    val id: Int,
    val name: String,
    val fieldOfActivity: String
)

@Serializable
data class Vacancy(
    val id: Int,
    val profession: String,
    val level: String,
    val salary: Int,
    val description: String
)

@Serializable
data class VacancyInfo(
    val id: Int,
    val profession: String,
    val level: String,
    val salary: Int,
    val companyName: String
)
