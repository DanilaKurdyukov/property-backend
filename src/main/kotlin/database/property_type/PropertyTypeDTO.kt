package ru.property.database.property_type

import kotlinx.serialization.Serializable

@Serializable
data class PropertyTypeDTO(
    val id: Int,
    val name: String
)