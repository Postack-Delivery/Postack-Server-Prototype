package com.postack.data

import com.postack.domain.data.CategoryDataSource
import com.postack.domain.models.Category
import com.postack.domain.models.SubCategory
import io.github.reactivecircus.cache4k.Cache
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.set
import org.litote.kmongo.setTo

class CategoryDataSourceImpl(
    db: CoroutineDatabase,
    private val cache: Cache<String, List<Category>>
) : CategoryDataSource {
    private val categoryCollection = db.getCollection<Category>()

    override suspend fun insertCategory(category: Category) {
        categoryCollection.insertOne(category)
        invalidateCache()
    }

    override suspend fun getAllCategories(): List<Category> {
        val cachedCategories = cache.get(ALL_CATEGORIES).orEmpty()
        if (cachedCategories.isNotEmpty()) return cachedCategories
        val remoteCategories = categoryCollection.find().toList()
        cache.put(ALL_CATEGORIES, remoteCategories)
        return remoteCategories
    }

    override suspend fun deleteCategory(id: String) {
        categoryCollection.deleteOne(Category::id eq id)
        invalidateCache()
    }
    override suspend fun updateCategory(category: Category) {
        categoryCollection.updateOne(
            Category::id eq category.id,
            set(
                Category::name setTo category.name,
                Category::subCategory setTo category.subCategory
            )
        )
        invalidateCache()
    }
    override suspend fun insertSubCategory(categoryId: String, name: String) {
        categoryCollection.find(Category::id eq categoryId).first()?.let {
            categoryCollection.updateOne(
                filter = Category::id eq it.id,
                set(
                    Category::subCategory setTo buildList {
                        addAll(it.subCategory)
                        add(SubCategory(name = name))
                    }
                )
            )
            invalidateCache()
        }
    }

    override suspend fun deleteSubcategory(categoryId: String, id: String) {
        categoryCollection.find(Category::id eq categoryId).first()?.let {
            categoryCollection.updateOne(
                filter = Category::id eq it.id,
                set(
                    Category::subCategory setTo buildList {
                        addAll(it.subCategory.filter { it.id != id })

                    }
                )
            )
            invalidateCache()
        }
    }

    override fun invalidateCache() {
        cache.invalidate(ALL_CATEGORIES)
    }

    companion object {
        const val ALL_CATEGORIES = "categories"
    }
}