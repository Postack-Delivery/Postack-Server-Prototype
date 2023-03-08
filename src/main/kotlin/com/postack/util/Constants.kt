package com.postack.util

object C {
    const val DATABASE = "postack_db"
    const val CONNECTION_URL = "mongodb+srv://Postack:Postack2022@postack.mqtqpxy.mongodb.net/?retryWrites=true&w=majority"
    const val DOCKER_USERNAME = "mwaibanda1"
    const val DOCKER_PASSWORD = "DockerHub-1978"
    const val PRODUCT_ID = "productId"
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

    const val CATEGORY_ID = "categoryId"
    const val CATEGORY_NAME = PRODUCT_NAME
    const val CATEGORY_SUBCATEGORY = PRODUCT_SUB_CATEGORY
    const val CATEGORY_COVER = PRODUCT_IMAGE

    const val SUPPLIER_CITY = "City"
    const val SUPPLIER_NAME = "Name"
    const val SUPPLIER_LOCATION = "Lat & Long"

    const val ADMIN_PASSWORD = "Postack2023"
    const val ADMIN_USERNAME = "admin"
    const val ADMIN_AUTH = "auth-form"
    const val ADMIN_SESSION = "auth-session"

    const val DELETE_ID = "Id"


    object Route {
        const val LOGIN = "/login"
        const val DASHBOARD = "/dashboard"
        const val STATIC = "/static"
        object API {
            const val CURRENT_VERSION = "/api/v1"
            const val PRODUCTS = "$CURRENT_VERSION/products"
            const val DELETE_PRODUCT = "$PRODUCTS/delete"
            const val UPDATE_PRODUCT = "$PRODUCTS/update"
            const val PRODUCT_VARIANT = "$CURRENT_VERSION/variants"
            const val ADD_VARIANT = "$PRODUCT_VARIANT/add"
            const val UPDATE_VARIANT = "$PRODUCT_VARIANT/update"
            const val DELETE_VARIANT = "$PRODUCT_VARIANT/delete"

            const val SUPPLIERS = "$CURRENT_VERSION/suppliers"
            const val UPDATE_SUPPLIER = "$SUPPLIERS/update"
            const val DELETE_SUPPLIER = "$SUPPLIERS/delete"
            const val CATEGORIES = "$CURRENT_VERSION/categories"
            const val SUB_CATEGORIES = "$CURRENT_VERSION/subcategories"
            const val ADD_SUB_CATEGORY = "$SUB_CATEGORIES/add"
            const val DELETE_SUB_CATEGORY = "$SUB_CATEGORIES/delete"
            const val UPDATE_CATEGORY = "$CATEGORIES/update"
            const val DELETE_CATEGORY = "$CATEGORIES/delete"


        }
    }
}