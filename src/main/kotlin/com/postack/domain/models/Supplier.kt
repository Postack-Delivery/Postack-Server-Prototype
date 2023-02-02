package com.postack.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Supplier(
    val name: String,
    val location: SupplierLocation
)

