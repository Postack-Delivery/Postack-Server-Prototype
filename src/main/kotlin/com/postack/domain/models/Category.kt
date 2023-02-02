package com.postack.domain.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Category(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String,
    val subCategory: List<SubCategory>,
    val cover: String
) {
    class Builder {
        private var name: String = ""
        private var cover: String = ""
        private var subCategory: List<SubCategory> = emptyList()

        fun name(name: String) = apply { this.name = name }
        fun cover(cover: String) = apply { this.cover = cover }
        fun subCategory(subCategory: List<SubCategory>) = apply { this.subCategory = subCategory }

        fun build() = Category(
            name = name,
            subCategory = subCategory,
            cover = cover
        )
    }

    override fun toString(): String {
        return subCategory.toString(category = name).drop(1).dropLast(1)
    }
}

fun List<SubCategory>.toString(category: String): String {
    var returnStr = ""
     this.forEach { str ->
        returnStr += "${str.toString(category)}, "
    }
    return "[${returnStr.dropLast(2)}]"
}

