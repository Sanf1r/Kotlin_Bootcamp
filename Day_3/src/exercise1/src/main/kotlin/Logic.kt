package org.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

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
        return "  $name\n  ${fieldOfActivity.lowercase().replaceFirstChar { it.uppercase() }}"
    }
}

@Serializable
data class CompanyList(
    val listOfCompanies: List<Company>
)

fun parseLogic(): JsonEnums {


    val summary = StringBuilder()
    fieldSelect()
    val field = Field.entries[input() - 1]
    summary.append("${field.value}. ")
    profSelect(summary)
    val prof = Profession.entries[inputProf() - 1]
    summary.append("${prof.value}. ")
    levelSelect(summary)
    val level = Level.entries[input() - 1]
    summary.append("${level.value}. ")
    salarySelect(summary)
    val summ = Salary.entries[input() - 1]
    summary.append("${summ.value}. ")
    println(summary.toString().trim())
    println("The list of suitable vacancies:")
    println()
    return JsonEnums(field, prof, level, summ)
}

fun filterLogic(list: List<Company>, jsonEnums: JsonEnums) {
    if (jsonEnums.field.value != "All") {
        list.filter { it.fieldOfActivity.equals(jsonEnums.field.value, true) }
    }
    if (jsonEnums.prof.value != "All") {
        list.filter { it -> it.vacancies.retainAll { it.profession.equals(jsonEnums.prof.value, true) } }
    }
    if (jsonEnums.level.value != "All") {
        list.filter { it -> it.vacancies.retainAll { it.level.equals(jsonEnums.level.value, true) } }
    }
    when (jsonEnums.summ) {
        Salary.LOW -> list.filter { it -> it.vacancies.retainAll { it.salary < 100000 } }
        Salary.MID -> list.filter { it -> it.vacancies.retainAll { it.salary in 100000..150000 } }
        Salary.TOP -> list.filter { it -> it.vacancies.retainAll { it.salary > 150000 } }
        else -> {}
    }
}

fun outputLogic(list: List<Company>) {
    var count = 1
    for (com in list) {
        for (vac in com.vacancies) {
            println("${count++}.")
            println(vac)
            println(com)
            println("---------------------------------------")
            println()
        }
    }
}
