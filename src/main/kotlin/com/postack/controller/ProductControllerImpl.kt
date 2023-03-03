package com.postack.controller

import com.postack.domain.controller.ProductController
import com.postack.domain.data.ProductDataSource
import com.postack.domain.models.Product
import com.postack.domain.models.ProductResponse
import com.postack.domain.models.ProductVariant

class ProductControllerImpl(
    private val productDataSource: ProductDataSource
): ProductController {
    override suspend fun addProduct(product: Product) {
        productDataSource.insertProduct(product)
    }

    override suspend fun addProductVariant(id: String, productVariant: ProductVariant) {
        productDataSource.insertProductVariant(id, productVariant)
    }

    override suspend fun updateProduct(product: Product) {
        productDataSource.updateProduct(product)
    }

    override suspend fun getAllProducts(page: Int): ProductResponse {
        return productDataSource.getAllProducts(page)
    }

    override suspend fun getCategoryRecommendedProducts(category: String, page: Int): ProductResponse {
        return productDataSource.getCategoryRecommendedProducts(category, page)
    }

    override suspend fun getProductsBySubCategory(subCategory: String, page: Int): ProductResponse {
        return productDataSource.getProductsBySubCategory(subCategory, page)
    }

    override suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): ProductResponse {
        return productDataSource.getProductsBySearchTerm(searchTerm, page)
    }

    override suspend fun deleteProduct(id: String) {
        productDataSource.deleteProduct(id)
    }
    override suspend fun deleteProductVariant(id: String, variantId: String) {
        productDataSource.deleteProductVariant(id, variantId)
    }
}