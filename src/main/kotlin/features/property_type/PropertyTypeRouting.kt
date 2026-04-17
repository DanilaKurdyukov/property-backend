package ru.property.features.property_type

import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.property.database.PropertyTypes
import ru.property.database.property_type.PropertyTypeDTO


fun Application.configurePropertyTypeRouting() {
    routing {
        get("/property-types") {
            val all = transaction {
                PropertyTypes.selectAll().map { row ->
                    PropertyTypeDTO(
                        id = row[PropertyTypes.id],
                        name = row[PropertyTypes.name]
                    )
                }
            }
            println(all)
            call.respond(all)
        }
    }
}