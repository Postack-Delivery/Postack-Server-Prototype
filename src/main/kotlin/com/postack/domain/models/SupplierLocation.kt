package com.postack.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class SupplierLocation(
    val lat: Long,
    val long: Long,
    val city: String
)