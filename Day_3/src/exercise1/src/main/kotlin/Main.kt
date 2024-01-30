package org.example

import kotlinx.serialization.json.Json

data class JsonEnums(val field: Field, val prof: Profession, val level: Level, val summ: Salary)

fun main() {
    val inputString = object {}.javaClass.getResourceAsStream("/listOfCompanies.json")?.bufferedReader()?.readText()
    val decodedJson = inputString?.let { Json.decodeFromString<CompanyList>(it) }

    val jsonEnums = parseLogic()

    val res = decodedJson!!.listOfCompanies

    filterLogic(res, jsonEnums)

    outputLogic(res)
}