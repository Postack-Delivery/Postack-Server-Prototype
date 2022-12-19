package com.postack.domain.models

import com.fasterxml.jackson.databind.BeanDescription
import kotlinx.css.Image
import kotlinx.html.DETAILS
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Locale.Builder
import java.util.Locale.Category

@Serializable
data class Product(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String = "",
    val price: Double = 0.0,
    val rating: Double = 3.5,
    val image: String = "",
    val description: String = "",
    val weight: Double = 0.0,
    val unitMeasure: String = "",
    val quantity: Int = 0,
    val category: String = "",
    val subCategory: String = "",
    val supplier: String = "",
) {
    class Builder {
        private var name: String = ""
        private var price: Double = 0.0
        private var image: String = ""
        private var description: String = ""
        private var weight: Double = 0.0
        private var unitMeasure: String = ""
        private var quantity: Int = 0
        private var category: String = ""
        private var subCategory: String = ""
        private var supplier: String = ""
        fun name(name: String) = apply { this.name = name }
        fun price(price: Double) = apply { this.price = price }
        fun image(image: String) = apply { this.image = image }
        fun description(description: String) = apply { this.description = description }
        fun weight(weight: Double) = apply { this.weight = weight }
        fun unitMeasure(unitMeasure: String) = apply { this.unitMeasure = unitMeasure }
        fun quantity(quantity: Int) = apply { this.quantity = quantity }
        fun category(category: String) = apply { this.category = category }
        fun subCategory(subCategory: String) = apply { this.subCategory = subCategory }
        fun supplier(supplier: String) = apply { this.supplier = supplier }
        fun build() = Product(
            name = name,
            price = price,
            description = description,
            image = image,
            weight = weight,
            unitMeasure = unitMeasure,
            supplier = supplier,
            quantity = quantity,
            category = category,
            subCategory = subCategory
        )
    }
}

@Serializable
class FoodOrder private constructor(
    val bread: String?,
    val condiments: String?,
    val meat: String?,
    val fish: String?) {

    data class Builder(
        var bread: String? = null,
        var condiments: String? = null,
        var meat: String? = null,
        var fish: String? = null) {

        fun bread(bread: String) = apply { this.bread = bread }
        fun condiments(condiments: String) = apply { this.condiments = condiments }
        fun meat(meat: String) = apply { this.meat = meat }
        fun fish(fish: String) = apply { this.fish = fish }
        fun build() = FoodOrder(bread, condiments, meat, fish)
    }
}


