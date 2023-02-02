package com.postack.domain.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class SubCategory(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String,
) {
    fun toString(category: String): String {
        return "{-id-: -$id-, -name-: -$name-, -category-: -${category.split(" ").first().lowercase()}-}"
    }
}