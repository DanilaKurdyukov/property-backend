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
        url = "jdbc:postgresql://dpg-d7gjo9vlk1mc7396sst0-a.oregon-postgres.render.com:5432/test_db_7b6s?sslmode=require",
        user = "test_user",
        driver = "org.postgresql.Driver",
        password = "NopjgBIkF3cSWl1kGZcHZUbFSKphGaT8",
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
