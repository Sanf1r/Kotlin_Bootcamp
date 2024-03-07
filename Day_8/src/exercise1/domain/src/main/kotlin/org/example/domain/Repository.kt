package org.example.domain

interface Repository {
    fun getCompanyList(): List<Company>
}