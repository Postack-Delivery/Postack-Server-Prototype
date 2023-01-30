package com.postack.domain.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Product(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String = "",
    val variants: List<ProductVariant>,
    val category: String = "",
    val subCategory: String = "",
    val supplier: String = "",
) {
    class Builder {
        private var name: String = ""
        private var category: String = ""
        private var subCategory: String = ""
        private var supplier: String = ""
        private var variants: List<ProductVariant> = emptyList()

        fun name(name: String) = apply { this.name = name }

        fun category(category: String) = apply { this.category = category }
        fun subCategory(subCategory: String) = apply { this.subCategory = subCategory }
        fun supplier(supplier: String) = apply { this.supplier = supplier }
        fun variants(variants: List<ProductVariant>) = apply { this.variants = variants }

        fun build() = Product(
            name = name,
            supplier = supplier,
            category = category,
            subCategory = subCategory,
            variants = variants
        )
    }
}


