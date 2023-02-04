package com.postack.data

import com.postack.domain.data.CategoryDataSource
import com.postack.domain.models.Category
import com.postack.domain.models.Product
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq

class CategoryDataSourceImpl(
    db: CoroutineDatabase
): CategoryDataSource {
    private val categoryCollection = db.getCollection<Category>()

    override suspend fun insertCategory(category: Category) {
        categoryCollection.insertOne(category)
    }

    override suspend fun getAllCategories(): List<Category> {
        return categoryCollection.find().toList()
    }

    override suspend fun deleteCategory(id: String) {
        categoryCollection.deleteOne(Category::id eq id)
    }
}