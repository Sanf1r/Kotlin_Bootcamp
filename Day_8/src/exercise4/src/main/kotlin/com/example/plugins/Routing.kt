package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.data.RepositoryImpl
import org.example.domain.GetCompanyByIdUseCase
import org.example.domain.GetCompanyListUseCase
import org.example.domain.GetVacanciesListUseCase
import org.example.domain.GetVacancyByIdUseCase

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello! My name is morfinov!")
        }

        get("/companies") {
            val repo = RepositoryImpl()
            val out = GetCompanyListUseCase(repo)
            call.respond(out.execute())
        }

        get("/companies/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            id?.let {
                val repo = RepositoryImpl()
                val out = GetCompanyByIdUseCase(repo)
                val company = out.execute(it - 1)
                company?.let {it2 ->
                    call.respond(it2)
                } ?: call.respondText("")
            } ?: call.respondText("")
        }

        get("/vacancies") {
            val repo = RepositoryImpl()
            val out = GetVacanciesListUseCase(repo)
            call.respond(out.execute())
        }

        get("/vacancies/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            id?.let {
                val repo = RepositoryImpl()
                val out = GetVacancyByIdUseCase(repo)
                val vacancy = out.execute(it - 1)
                vacancy?.let {it2 ->
                    call.respond(it2)
                } ?: call.respondText("")
            } ?: call.respondText("")
        }
    }
}
