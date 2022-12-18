package com.postack.domain.models

import com.fasterxml.jackson.databind.BeanDescription
import kotlinx.css.Image
import kotlinx.html.DETAILS
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Locale.Category

@Serializable
data class Product(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String,
    val price: Double,
    val image: String,
    val description: String,
    val weight: Double,
    val unitMeasure: String,
    val quantity: Int,
    val category: String,
    val subCategory: String,
    val supplier: String,
)
