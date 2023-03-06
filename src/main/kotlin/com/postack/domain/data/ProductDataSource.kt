package com.postack.domain.data

import com.postack.domain.models.Product
import com.postack.domain.models.ProductResponse
import com.postack.domain.models.ProductVariant

interface ProductDataSource {
    suspend fun insertProduct(product: Product)
    suspend fun insertProductVariant(id: String, productVariant: ProductVariant)
    suspend fun updateProduct(product: Product)
    suspend fun getAllProducts(page: Int): ProductResponse
    suspend fun getCategoryRecommendedProducts(category: String, page: Int): ProductResponse
    suspend fun getProductsBySubCategory(subCategory: String, page: Int): ProductResponse
    suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): ProductResponse
    suspend fun deleteProduct(id: String)
    suspend fun deleteProductVariant(id: String, variantId: String)
    fun invalidateCache()
}