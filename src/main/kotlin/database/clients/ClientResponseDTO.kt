package ru.property.database.clients

import kotlinx.serialization.Serializable

@Serializable
data class ClientResponseDTO(
    val id: Int,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val passportDataId: Int,
    val passportSeries: String,
    val passportNumber: String,
    val address: String,
    val budgetMin: Double,
    val budgetMax: Double,
    val interest: Int,
    val interestName: String,
    val photoUrl: String
)