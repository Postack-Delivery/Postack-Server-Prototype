package com.postack.domain.controller

import com.postack.domain.models.Product

interface ProductController {
    suspend fun addProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun getAllProducts(page: Int): List<Product>
    suspend fun getCategoryRecommendedProducts(category: String, page: Int): List<Product>
    suspend fun getProductsBySubCategory(subCategory: String, page: Int): List<Product>
    suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): List<Product>
    suspend fun deleteProduct(id: String)
}