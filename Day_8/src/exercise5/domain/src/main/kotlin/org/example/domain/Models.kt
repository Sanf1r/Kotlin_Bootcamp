package org.example.domain

import kotlinx.serialization.*
import java.time.LocalDate

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

@Serializable
data class Contacts(
    val phone: String,
    val email: String,
)

@Serializable
data class Education(
    val type: String,
    @Serializable(with = DateYearSerializer::class)
    val yearStart: LocalDate,
    @Serializable(with = DateYearSerializer::class)
    val yearEnd: LocalDate,
    val description: String
)

@Serializable
data class JobExperience(
    @Serializable(with = DateMonYearSerializer::class)
    val dateStart: LocalDate,
    @Serializable(with = DateMonYearSerializer::class)
    val dateEnd: LocalDate,
    val companyName: String,
    val description: String
)

@Serializable
data class Candidate(
    val name: String,
    val profession: String,
    val sex: String,
    @Serializable(with = DateDotSerializer::class)
    val birthDate: LocalDate,
    val contacts: Contacts,
    val relocation: String
)

@Serializable
data class CandidateInfo(
    val id: Int,
    val candidate: Candidate,
    val education: List<Education>,
    val jobExperience: List<JobExperience>,
    val freeForm: String
)

