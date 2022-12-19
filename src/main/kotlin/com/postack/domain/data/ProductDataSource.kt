package com.postack.domain.data

import com.postack.domain.models.Product
import com.postack.domain.models.ProductResponse

interface ProductDataSource {
    suspend fun insertProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun getAllProducts(page: Int): ProductResponse
    suspend fun getCategoryRecommendedProducts(category: String, page: Int): ProductResponse
    suspend fun getProductsBySubCategory(subCategory: String, page: Int): ProductResponse
    suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): ProductResponse
    suspend fun deleteProduct(id: String)
}