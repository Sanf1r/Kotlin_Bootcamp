package org.example

import kotlinx.serialization.json.Json

fun main() {
    val resumeString = object {}.javaClass.getResourceAsStream("/resume.json")?.bufferedReader()?.readText()
    val resumeObj = resumeString?.let { Json.decodeFromString<CandidateInfo>(it) }

    val companyString = object {}.javaClass.getResourceAsStream("/listOfCompanies.json")?.bufferedReader()?.readText()
    val companyObj = companyString?.let { Json.decodeFromString<CompanyList>(it) }

    if (resumeObj != null && companyObj != null) {
            outputLogic(resumeObj, companyObj)
    } else {
        println("Problem with resume or company files!")
    }
}
