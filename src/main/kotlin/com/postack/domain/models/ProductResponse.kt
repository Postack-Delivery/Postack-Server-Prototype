package com.postack.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val data: List<Product>,
    val page: Int,
    val totalPages: Int
)
