package com.postack.controller

import com.postack.domain.controller.ProductController
import com.postack.domain.data.ProductDataSource
import com.postack.domain.models.Product

class ProductControllerImpl(
    private val productDataSource: ProductDataSource
): ProductController {
    override suspend fun addProduct(product: Product) {
        productDataSource.insertProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        productDataSource.updateProduct(product)
    }

    override suspend fun getAllProducts(page: Int): List<Product> {
        return productDataSource.getAllProducts(page)
    }

    override suspend fun getCategoryRecommendedProducts(category: String, page: Int): List<Product> {
        return productDataSource.getCategoryRecommendedProducts(category, page)
    }

    override suspend fun getProductsBySubCategory(subCategory: String, page: Int): List<Product> {
        return productDataSource.getProductsBySubCategory(subCategory, page)
    }

    override suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): List<Product> {
        return productDataSource.getProductsBySearchTerm(searchTerm, page)
    }

    override suspend fun deleteProduct(id: String) {
        productDataSource.deleteProduct(id)
    }
}