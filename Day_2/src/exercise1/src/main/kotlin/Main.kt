package com.example.kotlincourse

import com.example.kotlincourse.data.*
import com.example.kotlincourse.utils.descriptions
import com.example.kotlincourse.utils.phones
import kotlin.random.Random

fun inputZone(): BaseZone {
    val x: BaseZone
    val twoDigitsSemi = "-?([1-9](\\d+)?([.]\\d+)?|0([.]\\d+)?);" +
            "-?([1-9](\\d+)?([.]\\d+)?|0([.]\\d+)?)"
    while (true) {
        val str = readln()
        if (str.matches(
                ("^$twoDigitsSemi\\s" +
                        "([1-9](\\d+)?([.]\\d+)?|0[.]\\d+)\$").toRegex()
            )
        ) {
            val parts = str.split(';', ' ')
            x = CircleZone(Circle(parts[0].toDouble(), parts[1].toDouble(), parts[2].toDouble()))
            break
        } else if (str.matches(
                ("^$twoDigitsSemi\\s" +
                        "$twoDigitsSemi\\s" +
                        "$twoDigitsSemi\$").toRegex()
            )
        ) {
            val parts = str.split(';', ' ')
            x = TriangleZone(
                Triangle(
                    parts[0].toDouble(), parts[1].toDouble(),
                    parts[2].toDouble(), parts[3].toDouble(),
                    parts[4].toDouble(), parts[5].toDouble()
                )
            )
            break
        } else if (str.matches(
                ("^$twoDigitsSemi\\s" +
                        "$twoDigitsSemi\\s" +
                        "$twoDigitsSemi\\s" +
                        "$twoDigitsSemi\$").toRegex()
            )
        ) {
            val parts = str.split(';', ' ')
            x = TetragonZone(
                Tetragon(
                    parts[0].toDouble(), parts[1].toDouble(),
                    parts[2].toDouble(), parts[3].toDouble(),
                    parts[4].toDouble(), parts[5].toDouble(),
                    parts[6].toDouble(), parts[7].toDouble()
                )
            )
            break

        } else {
            println("Couldn't parse zone info. Please, try again")
            continue
        }
    }
    return x
}

fun inputIncident(): Incident {
    val res: Incident
    var str: String
    while (true) {
        str = readln()
        if (str.matches(
                ("^-?([1-9](\\d+)?([.]\\d+)?|0([.]\\d+)?);" +
                        "-?([1-9](\\d+)?([.]\\d+)?|0([.]\\d+)?)\$").toRegex()
            )
        ) {
            val parts = str.split(';')
            val randDisc = descriptions[Random.nextInt(descriptions.size)]
            val randPhone = phones[Random.nextInt(phones.size)]
            val randType = findType(randDisc)
            val type = IncidentType.values().getOrNull(randType)
            res = Incident(parts[0].toDouble(), parts[1].toDouble(), type, randPhone, randDisc)
            break
        } else {
            println("Couldn't parse incident info. Please, try again")
            continue
        }
    }
    return res
}

fun findType(str: String): Int {
    val result = if (str.contains("fire".toRegex())) {
        0
    } else if (str.contains("gas".toRegex())) {
        1
    } else if (str.contains("cat".toRegex())) {
        2
    } else {
        4
    }
    return result
}


fun main() {
    println("Enter zone parameters:")
    val zone = inputZone()
    println()
    println("The zone info:")
    println("The shape of zone: ${zone.shape}")
    println("Phone number: ${zone.phone}")
    println()
    println("Enter an incident coordinates:")
    val inc = inputIncident()
    println()
    println("The incident info:")
    println("Description: ${inc.disc}")
    inc.phone?.let { println("Phone number: $it") }
    inc.type?.let { println("Type: $it") }
    println()
    if (zone.determ(inc)) {
        println("An incident is within the zone")
    } else {
        println("An incident is not in the zone")
        println("Switch the applicant to the common number: 88008473824")
    }
}