package com.postack.domain.models

import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class ProductVariant(
    val id: String = ObjectId().toString(),
    val uniqueName: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val image: String = "",
    val weight: Double = 0.0,
    val unitMeasure: String = "",
    val quantity: Int = 0,
    val rating: Double = 3.5

) {
    class Builder {
        private var uniqueName: String = ""
        private var price: Double = 0.0
        private var image: String = ""
        private var description: String = ""
        private var rating: Double = 3.5
        private var weight: Double = 0.0
        private var unitMeasure: String = ""
        private var quantity: Int = 0
        fun price(price: Double) = apply { this.price = price }
        fun uniqueName(uniqueName: String) = apply { this.uniqueName = uniqueName }
        fun image(image: String) = apply { this.image = image }

        fun description(description: String) = apply { this.description = description }
        fun weight(weight: Double) = apply { this.weight = weight }
        fun rating(rating: Double) = apply { this.rating = rating }
        fun unitMeasure(unitMeasure: String) = apply { this.unitMeasure = unitMeasure }
        fun quantity(quantity: Int) = apply { this.quantity = quantity }

        fun build() = ProductVariant(
            uniqueName = uniqueName,
            price = price,
            description = description,
            image = image,
            weight = weight,
            unitMeasure = unitMeasure,
            quantity = quantity,
            rating = rating
        )
    }
    data class JsVariant(
        val id: String,
        val name: String
    ) {
        override fun toString(): String {
            return "{-id-: -$id-, -name-: -$name-}"
        }
    }
    fun toJsVariant() = JsVariant(
        id = id,
        name = uniqueName
    )
}