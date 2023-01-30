package com.postack.data

import com.postack.domain.data.ProductDataSource
import com.postack.domain.models.Product
import com.postack.domain.models.ProductResponse
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq
import org.litote.kmongo.ne

class ProductDataSourceImpl(
    db: CoroutineDatabase
) : ProductDataSource {
    private val productsCollection = db.getCollection<Product>()

    override suspend fun insertProduct(product: Product) {
        productsCollection.insertOne(product)
    }

    override suspend fun updateProduct(product: Product) {
        productsCollection.updateOne(Product::id eq product.id, product)
    }

    override suspend fun getAllProducts(page: Int): ProductResponse {
        val data = productsCollection.find()
        return ProductResponse(
            data = data
                .skip(12 * (page - 1))
                .limit(12).toList(),
            page = page,
            totalPages = if (data.toList().size / 12 == 0) 1 else data.toList().size / 12
        )
    }

    override suspend fun getCategoryRecommendedProducts(category: String, page: Int): ProductResponse {
        val data = productsCollection.find(Product::category eq category)
        return ProductResponse(
            data = data
                .toList()
                .shuffled()
                .takeLast(12),
            page = page,
            totalPages = if (data.toList().size / 12 == 0) 1 else data.toList().size / 12
        )
    }

    override suspend fun getProductsBySubCategory(subCategory: String, page: Int): ProductResponse {
        val data = productsCollection.find(Product::subCategory eq subCategory)
        return ProductResponse(
            data = data
                .skip(12 * (page - 1))
                .limit(12)
                .toList(),
            page = page,
            totalPages = if (data.toList().size / 12 == 0) 1 else data.toList().size / 12
        )
    }

    override suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): ProductResponse {
        val data = productsCollection.find()
            .toList().filter { it.name.lowercase().contains(searchTerm.lowercase()) }
        return ProductResponse(
            data = data,
            page = page,
            totalPages = if (data.size / 12 == 0) 1 else data.size / 12
        )
    }

    override suspend fun deleteProduct(id: String) {
        productsCollection.deleteOne(Product::id eq id)
    }
}