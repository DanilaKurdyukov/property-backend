package ru.property.database

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.property.UserService

//jdbc:postgresql://HOST:PORT/DB_NAME?sslmode=require

fun Application.configureDatabases() {
    val database = Database.connect(
        url = System.getenv("DB_URL"),
        user = System.getenv("DB_USER"),
        driver = System.getenv("DB_DRIVER") ?: "org.postgresql.Driver",
        password = System.getenv("DB_PASSWORD"),
    )
    val userService = UserService(database)
    routing {
        get("/db-test") {
            try {
                transaction(database) {
                    exec("SELECT 1;")
                }
                call.respond(HttpStatusCode.OK, "DB connection is OK")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "DB error: ${e.message}")
            }
        }

    }
}
