package com.postack.domain.controller

import com.postack.domain.models.Product
import com.postack.domain.models.ProductResponse

interface ProductController {
    suspend fun addProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun getAllProducts(page: Int): ProductResponse
    suspend fun getCategoryRecommendedProducts(category: String, page: Int): ProductResponse
    suspend fun getProductsBySubCategory(subCategory: String, page: Int): ProductResponse
    suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): ProductResponse
    suspend fun deleteProduct(id: String)
}