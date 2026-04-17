package ru.property.database

import org.jetbrains.exposed.sql.Table

object PropertyTypes : Table("property_types") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)

    override val primaryKey = PrimaryKey(id)
}