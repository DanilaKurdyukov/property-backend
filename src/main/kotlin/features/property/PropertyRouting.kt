package ru.property.features.property

import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.property.database.Properties
import ru.property.database.PropertyPhotos
import ru.property.database.PropertyStatuses
import ru.property.database.PropertyTypes
import ru.property.database.properties.PropertyResponseDTO

fun Application.configurePropertyRouting() {
    routing {
        get("/properties") {
            val result = transaction {
                (Properties innerJoin PropertyTypes innerJoin PropertyStatuses leftJoin PropertyPhotos)
                    .selectAll()
                    .where { PropertyPhotos.isMain eq true }
                    .map { row ->
                        PropertyResponseDTO(
                            id = row[Properties.id],
                            street = row[Properties.street],
                            city = row[Properties.city],
                            number = row[Properties.number],
                            district = row[Properties.district],
                            area = row[Properties.area],
                            roomsCount = row[Properties.roomsCount],
                            floor = row[Properties.floor],
                            description = row[Properties.description],
                            price = row[Properties.price].toDouble(),
                            type = row[PropertyTypes.name],
                            status = row[PropertyStatuses.name],
                            typeId = row[PropertyTypes.id],
                            statusId = row[PropertyStatuses.id],
                            photoUrl = row[PropertyPhotos.photoUrl]
                        )

                    }

            }
            call.respond(result)
        }
    }
}