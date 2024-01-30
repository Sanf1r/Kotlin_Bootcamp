package org.example

import kotlinx.serialization.json.Json

fun main() {
    val inputString = object {}.javaClass.getResourceAsStream("/resume.json")?.bufferedReader()?.readText()
    val decodedJson = inputString?.let { Json.decodeFromString<CandidateInfo>(it) }
    println("Block 1")
    println(decodedJson)
    println()
    println("Block 2")
    if (decodedJson != null) {
        println(decodedJson.education)
    }
    println()
    println("Block 3")
    if (decodedJson != null) {
        println(decodedJson.jobExperience)
    }
}