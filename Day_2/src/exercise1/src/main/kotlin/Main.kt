package com.example.kotlincourse

import com.example.kotlincourse.utils.*

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
    inc.type?.let { println("Type: ${it.value}") }
    println()
    if (zone.determ(inc)) {
        println("An incident is within the zone")
    } else {
        println("An incident is not in the zone")
        println("Switch the applicant to the common number: 88008473824")
    }
}