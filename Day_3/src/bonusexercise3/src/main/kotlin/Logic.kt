package org.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import java.time.LocalDate
import java.time.Period

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

@Serializable
data class Vacancies(
    val profession: String,
    val level: String,
    val salary: Int
) {
    override fun toString(): String {
        return "${level.lowercase().replaceFirstChar { it.uppercase() }} ${
            profession.lowercase().replaceFirstChar { it.uppercase() }
        }   ---   $salary"
    }
}

@Serializable
data class Company(
    val name: String,
    @SerialName("field_of_activity")
    val fieldOfActivity: String,
    val vacancies: MutableList<Vacancies>,
    val contacts: String
) {
    override fun toString(): String {
        return "\n$name\n${fieldOfActivity.lowercase().replaceFirstChar { it.uppercase() }}\n$vacancies"
    }
}

@Serializable
data class CompanyList(
    val listOfCompanies: List<Company>
)

fun outputLogic(resumeObj : CandidateInfo, companyObj: CompanyList) {
    var months = 0
    for (job in resumeObj.jobExperience) {
        val per = Period.between(job.dateStart, job.dateEnd)
        months += per.months
    }

    val grade = when (months) {
        in 0..35 -> "junior"
        in 36..72 -> "middle"
        else -> "senior"
    }

    println("The candidate:")
    println("Name: ${resumeObj.candidate.name}")
    println("Profession: ${resumeObj.candidate.profession}")
    println("Experience: ${months / 12} years ${months % 12} months ($grade)")
    println("Suitable vacancies:")
    val toFilter = companyObj.listOfCompanies
    toFilter.forEach { it -> it.vacancies.retainAll { it.profession.equals(resumeObj.candidate.profession, true) } }
    toFilter.forEach { it -> it.vacancies.retainAll { it.level == grade } }
    for (com in toFilter.filter { it.vacancies.isNotEmpty() }) {
        for (vac in com.vacancies) {
            println(com.name)
            println("Field of activity: ${com.fieldOfActivity}")
            println("Candidate level: ${vac.level}")
            println("Salary: ${vac.salary}")
            println("Contacts: ${com.contacts}")
        }
    }
}
