package org.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import java.time.LocalDate

@Serializable
data class Contacts(
    val phone: String,
    val email: String,
)

@Serializable
data class Education(
    val type: String,
    @Serializable(with = DateYearSerializer::class)
    @SerialName("year_start")
    val yearStart: LocalDate,
    @Serializable(with = DateYearSerializer::class)
    @SerialName("year_end")
    val yearEnd: LocalDate,
    val description: String
) {
    override fun toString(): String {
        return "Education(type=$type, year_start=${yearStart.year}, year_end=${yearEnd.year}, description=$description)"
    }
}

@Serializable
data class JobExperience(
    @Serializable(with = DateMonYearSerializer::class)
    @SerialName("date_start")
    val dateStart: LocalDate,
    @Serializable(with = DateMonYearSerializer::class)
    @SerialName("date_end")
    val dateEnd: LocalDate,
    @SerialName("company_name")
    val companyName: String,
    val description: String
)

@Serializable
data class Candidate(
    val name: String,
    val profession: String,
    val sex: String,
    @Serializable(with = DateDotSerializer::class)
    @SerialName("birth_date")
    val birthDate: LocalDate,
    val contacts: Contacts,
    val relocation: String
) {
    override fun toString(): String {
        return "name=$name, profession=$profession, sex=$sex, birth_date=$birthDate, contacts=$contacts, relocation=$relocation"
    }
}

@Serializable
data class CandidateInfo(
    @SerialName("candidate_info")
    val candidate: Candidate,
    val education: List<Education>,
    @SerialName("job_experience")
    val jobExperience: List<JobExperience>,
    @SerialName("free_form")
    val freeForm: String
) {
    override fun toString(): String {
        return "CandidateInfo($candidate, free_form=$freeForm)"
    }
}
