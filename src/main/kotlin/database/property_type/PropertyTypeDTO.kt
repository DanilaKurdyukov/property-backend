package ru.property.database.`property-type`

import kotlinx.serialization.Serializable

@Serializable
data class PropertyTypeDTO(
    val id: Int,
    val name: String
)