package ru.property.database.properties

import kotlinx.serialization.Serializable

@Serializable
data class PropertyResponseDTO(
    val id: Int,
    val street: String,
    val city: String,
    val number: String,
    val district: String,
    val area: Int,
    val roomsCount: Int,
    val floor: Int,
    val description: String,
    val price: Double,
    val type: String,
    val status: String
)