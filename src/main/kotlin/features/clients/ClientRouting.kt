package ru.property.features.clients

import io.ktor.server.application.Application
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.property.database.Clients
import ru.property.database.PassportData
import ru.property.database.PropertyTypes
import ru.property.database.clients.ClientResponseDTO

fun Application.configureClientRouting() {
    routing {
        get("/clients") {
            val result = transaction {
                (Clients innerJoin PassportData innerJoin PropertyTypes)
                    .selectAll()
                    .map { row ->
                        ClientResponseDTO(
                            id = row[Clients.id],
                            firstName = row[Clients.firstName],
                            middleName = row[Clients.middleName],
                            lastName = row[Clients.lastName],
                            phone = row[Clients.phone],
                            email = row[Clients.email],
                            passportDataId = row[Clients.passportDataId],
                            passportSeries = row[PassportData.series],
                            passportNumber = row[PassportData.number],
                            address = row[Clients.address],
                            budgetMin = row[Clients.budgetMin].toDouble(),
                            budgetMax = row[Clients.budgetMax].toDouble(),
                            interest = row[Clients.interest],
                            interestName = row[PropertyTypes.name],
                        )
                    }
            }
        }
    }
}