package com.postack.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class SupplierLocation(
    val lat: Float,
    val long: Float,
    val city: String
) {
    class Builder {
        private var lat: Float = 0f
        private var long: Float = 0f
        private var city: String = ""
        fun lat(lat: Float) = apply { this.lat = lat }
        fun long(long: Float) = apply { this.long = long }
        fun city(city: String) = apply { this.city = city }
        fun build() = SupplierLocation(
            lat = lat,
            long = long,
            city = city
        )
    }
}