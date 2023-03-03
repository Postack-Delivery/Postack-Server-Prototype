package com.postack.domain.controller

import com.postack.domain.models.Product
import com.postack.domain.models.ProductResponse
import com.postack.domain.models.ProductVariant

interface ProductController {
    suspend fun addProduct(product: Product)
    suspend fun addProductVariant(id: String, productVariant: ProductVariant)
    suspend fun getAllProducts(page: Int): ProductResponse
    suspend fun getCategoryRecommendedProducts(category: String, page: Int): ProductResponse
    suspend fun getProductsBySubCategory(subCategory: String, page: Int): ProductResponse
    suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): ProductResponse
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(id: String)
    suspend fun deleteProductVariant(id: String, variantId: String)
}