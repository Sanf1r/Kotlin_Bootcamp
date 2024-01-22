package com.example.kotlincourse.data

data class Incident(
    val x: Double,
    val y: Double,
    val type: IncidentType?,
    val phone: String?,
    val disc: String?
)

enum class IncidentType(val value: String) {
    FIRE("Fire"),
    GAS("Gas leak"),
    CAT("Cat on the tree")
}
