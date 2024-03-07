package org.example.domain

import kotlinx.serialization.*

@Serializable
data class Company(
    val name: String,
    val fieldOfActivity: String,
    val vacancies: List<String> = emptyList(),
    val contacts: String
)