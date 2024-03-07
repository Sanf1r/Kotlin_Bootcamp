package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.data.RepositoryImpl
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
    }
}
