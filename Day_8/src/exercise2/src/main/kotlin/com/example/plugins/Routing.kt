package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.data.RepositoryImpl
import org.example.domain.GetCompanyById
import org.example.domain.GetCompanyListUseCase

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello morfinov!")
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
                val out = GetCompanyById(repo)
                val company = out.execute(it - 1)
                company?.let {it2 ->
                    call.respond(it2)
                } ?: call.respondText("")
            } ?: call.respondText("")
        }
    }
}
