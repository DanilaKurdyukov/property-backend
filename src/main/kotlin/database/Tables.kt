package ru.property.database

import org.jetbrains.exposed.sql.Table

object PropertyTypes : Table("property_types") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)

    override val primaryKey = PrimaryKey(id)
}

object PropertyStatuses : Table("property_statuses") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)

    override val primaryKey = PrimaryKey(id)
}

object Properties : Table("properties") {
    val id = integer("id").autoIncrement()
    val street = varchar("street", 50)
    val city = varchar("city", 50)
    val number = varchar("number", 50)
    val district = varchar("district", 50)
    val area = integer("area")
    val roomsCount = integer("rooms_count")
    val floor = integer("floor")
    val description = varchar("description", 100)

    val price = decimal("price", precision = 10, scale = 2)


    val typeId = reference("type_id", PropertyTypes.id)
    val statusId = reference("status_id", PropertyStatuses.id)


    override val primaryKey = PrimaryKey(id)
}

object PropertyPhotos : Table("property_photos") {
    val id = integer("id").autoIncrement()
    val propertyId = reference("property_id", Properties.id)
    val photoUrl = varchar("photo_url", 1024)
    val isMain = bool("is_main")

    override val primaryKey = PrimaryKey(PropertyPhotos.id)
}