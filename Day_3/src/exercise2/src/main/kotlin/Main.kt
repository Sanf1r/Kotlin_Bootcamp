package org.example

import kotlinx.serialization.json.Json
import java.time.Period

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


    var res = 0
    if (decodedJson != null) {
        for (job in decodedJson.jobExperience) {
            var per = Period.between(job.dateStart,job.dateEnd)
            res += per.months
        }

    }
    println(res)
    println(res)

    var y = res / 12
    var m = res % 12

    println("$y year - $m months")

    }
