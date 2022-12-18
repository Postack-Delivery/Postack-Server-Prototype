package com.postack.domain.data

import com.postack.domain.models.Product

interface ProductDataSource {
    suspend fun insertProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun getAllProducts(page: Int): List<Product>
    suspend fun getCategoryRecommendedProducts(category: String, page: Int): List<Product>
    suspend fun getProductsBySubCategory(subCategory: String, page: Int): List<Product>
    suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): List<Product>
    suspend fun deleteProduct(id: String)
}