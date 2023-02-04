package com.postack.domain.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Supplier(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String,
    val location: SupplierLocation
) {
    class Builder {
        private var id: String = ""
        private var name: String = ""
        private var location: SupplierLocation? = null
        fun id(id: String) = apply { this.id = id }

        fun name(name: String) = apply { this.name = name }
        fun location(location: SupplierLocation) = apply { this.location = location }

        fun build() = Supplier(
            id = id,
            name = name,
            location = location!!
        )
    }
    override fun toString(): String {
        return "{'id': '$id', 'name': '$name', 'location': '${location.lat}, ${location.long}', 'city': '${location.city}'}"
    }
}

