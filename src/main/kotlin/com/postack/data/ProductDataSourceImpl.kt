package com.postack.data

import com.postack.domain.data.ProductDataSource
import com.postack.domain.models.Product
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq

class ProductDataSourceImpl(
    db: CoroutineDatabase
): ProductDataSource {
    private val productsCollection = db.getCollection<Product>()

    override suspend fun insertProduct(product: Product) {
        productsCollection.insertOne(product)
    }

    override suspend fun updateProduct(product: Product) {
        productsCollection.updateOne(Product::id eq product.id, product)
    }

    override suspend fun getAllProducts(page: Int): List<Product> {
        return productsCollection.find()
            .skip(12 * page - 1)
            .limit(12).toList()
    }

    override suspend fun getCategoryRecommendedProducts(category: String, page: Int): List<Product> {
        return productsCollection.find(Product::category eq category)
            .toList()
            .shuffled()
            .takeLast(12)
    }

    override suspend fun getProductsBySubCategory(subCategory: String, page: Int): List<Product> {
        return productsCollection.find(Product::subCategory eq subCategory)
            .skip(12 * page - 1)
            .limit(12)
            .toList()
    }

    override suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): List<Product> {
        return productsCollection.find(Product::name eq searchTerm).toList()
    }

    override suspend fun deleteProduct(id: String) {
        productsCollection.deleteOne(Product::id eq  id)
    }
}