package org.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import java.io.File
import java.io.InputStream

@Serializable
data class Vacancies(
    val profession: String,
    val level: String,
    val salary: Int
)

@Serializable
data class Company(
    val name: String,
    @SerialName("field_of_activity")
    val fieldOfActivity: String,
    val vacancies: List<Vacancies>,
    val contacts: String
)

@Serializable
data class CompanyList(
    val listOfCompanies: List<Company>
)



fun input(): Int {
    val x: Int
    while (true) {
        val str = readln()
        if (str.matches("^[1-4]\$".toRegex())) {
            x = str.toInt()
            println()
            break
        } else {
            println("It doesn't look like a correct input.")
            continue
        }
    }
    return x
}

fun inputProf(): Int {
    val x: Int
    while (true) {
        val str = readln()
        if (str.matches("^[1-6]\$".toRegex())) {
            x = str.toInt()
            println()
            break
        } else {
            println("It doesn't look like a correct input.")
            continue
        }
    }
    return x
}

fun main() {
    val inputStream: InputStream =
        File("C:\\Users\\natas\\Kotlin_Bootcamp\\Day_3\\src\\listOfCompanies.json").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }

    val decodedJson = Json.decodeFromString<CompanyList>(inputString)
    val summary = StringBuilder()
    fieldSelect()
    var choice = input()
    summary.append("${Field.values()[choice - 1].value}. ")
    profSelect(summary)
    choice = inputProf()
    summary.append("${Profession.values()[choice - 1].value}. ")
    levelSelect(summary)
    choice = input()
    summary.append("${Level.values()[choice - 1].value}. ")
    salarySelect(summary)
    choice = input()
    summary.append("${Salary.values()[choice - 1].value}. ")
    println(summary.toString().trim())
    println("The list of suitable vacancies:")
    println()

//    var res = decodedJson.listOfCompanies.filter { it.fieldOfActivity == "banking" }
//
//    res = res.filter { it -> it.vacancies.any { it.profession == "developer" } }
//
//    res = res.filter { it -> it.vacancies.any { it.level == "junior" } }
//
//    res = res.filter { it -> it.vacancies.any { it.salary < 100000 } }
//
//        for (com in res) {
//            println(com)
//            println("--------")
//        }
}