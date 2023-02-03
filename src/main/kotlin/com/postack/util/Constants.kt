package com.postack.util

object C {
    const val DATABASE = "postack_db"
    const val CONNECTION_URL = "mongodb+srv://Postack:Postack2022@postack.mqtqpxy.mongodb.net/?retryWrites=true&w=majority"

    const val PRODUCT_NAME = "name"
    const val PRODUCT_PRICE = "price"
    const val PRODUCT_DESCRIPTION = "description"
    const val PRODUCT_WEIGHT = "weight"
    const val PRODUCT_SUPPLIER = "supplier"
    const val PRODUCT_CATEGORY = "category"
    const val PRODUCT_SUB_CATEGORY = "sub-category"
    const val PRODUCT_UNIT_MEASURE = "unit"
    const val PRODUCT_QUANTITY = "quantity"
    const val PRODUCT_IMAGE = "image"
    const val PRODUCT_VARIANT = "variant"

    const val CATEGORY_NAME = PRODUCT_NAME
    const val CATEGORY_SUBCATEGORY = PRODUCT_SUB_CATEGORY
    const val CATEGORY_COVER = PRODUCT_IMAGE

    const val ADMIN_PASSWORD = "Postack2023"
    const val ADMIN_USERNAME = "admin"
    const val ADMIN_AUTH = "auth-form"
    const val ADMIN_SESSION = "auth-session"

    object Route {
        const val LOGIN = "/login"
        const val DASHBOARD = "/dashboard"
        const val STATIC = "/static"
        object API {
            const val DOCUMENTATION = "/api/v1"
            const val SUPPLIERS = "/api/v1/suppliers"
            const val PRODUCTS = "/api/v1/products"
            const val CATEGORIES = "/api/v1/categories"
        }
    }
}