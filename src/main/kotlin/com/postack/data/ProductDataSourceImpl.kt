package com.postack.data

import com.postack.domain.data.ProductDataSource
import com.postack.domain.models.Product
import com.postack.domain.models.ProductResponse
import io.github.reactivecircus.cache4k.Cache
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ProductDataSourceImpl(
    db: CoroutineDatabase,
    private val cache: Cache<String, List<Product>>
) : ProductDataSource {
    private val productsCollection = db.getCollection<Product>()

    override suspend fun insertProduct(product: Product) {
        productsCollection.insertOne(product)
        cache.invalidate(ALL_PRODUCTS)
    }

    override suspend fun updateProduct(product: Product) {
        productsCollection.updateOne(Product::id eq product.id, product)
        cache.invalidate(ALL_PRODUCTS)
    }

    override suspend fun getAllProducts(page: Int): ProductResponse {
        val cachedProducts = cache.get(ALL_PRODUCTS).orEmpty()
        if (cachedProducts.isNotEmpty()) return ProductResponse(
            data = cachedProducts.chunked(12)[page - 1],
            page = page,
            totalPages = cachedProducts.chunked(12).size
        )
        val data = productsCollection.find()
        cache.put(ALL_PRODUCTS, data.toList())
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
        val cachedProducts = cache.get(PRODUCTS_FOR_SUBCATEGORY).orEmpty()
        if (cachedProducts.isNotEmpty()) return ProductResponse(
            data = cachedProducts.chunked(12)[page - 1],
            page = page,
            totalPages = cachedProducts.chunked(12).size
        )
        val remoteProducts = productsCollection.find(Product::subCategory eq subCategory)
        cache.put(PRODUCTS_FOR_SUBCATEGORY, remoteProducts.toList())
        return ProductResponse(
            data = remoteProducts
                .skip(12 * (page - 1))
                .limit(12)
                .toList(),
            page = page,
            totalPages = if (remoteProducts.toList().size / 12 == 0) 1 else remoteProducts.toList().size / 12
        )
    }

    override suspend fun getProductsBySearchTerm(searchTerm: String, page: Int): ProductResponse {
        val data = productsCollection.find().toList().filter { it.name.lowercase().contains(searchTerm.lowercase()) }
        return ProductResponse(
            data = data,
            page = page,
            totalPages = if (data.size / 12 == 0) 1 else data.size / 12
        )
    }

    override suspend fun deleteProduct(id: String) {
        productsCollection.deleteOne(Product::id eq id)
        cache.invalidate(ALL_PRODUCTS)
    }
    companion object {
        const val ALL_PRODUCTS = "all-products"
        const val PRODUCTS_FOR_SUBCATEGORY = "products-for-subcategory"
    }
}