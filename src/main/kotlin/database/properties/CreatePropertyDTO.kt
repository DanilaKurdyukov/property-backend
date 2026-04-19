package ru.property.database.properties

import kotlinx.serialization.Serializable

@Serializable
data class CreatePropertyDTO(
    val street: String,
    val city: String,
    val number: String,
    val district: String,
    val area: Int,
    val roomsCount: Int,
    val floor: Int,
    val description: String,
    val price: Double,
    val typeId: Int,
    val statusId: Int
)