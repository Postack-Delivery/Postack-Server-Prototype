package com.postack.data

import com.postack.domain.data.CategoryDataSource
import com.postack.domain.models.Category
import com.postack.domain.models.Product
import com.postack.domain.models.SubCategory
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq
import org.litote.kmongo.set
import org.litote.kmongo.setTo

class CategoryDataSourceImpl(
    db: CoroutineDatabase
) : CategoryDataSource {
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
        }
    }
}