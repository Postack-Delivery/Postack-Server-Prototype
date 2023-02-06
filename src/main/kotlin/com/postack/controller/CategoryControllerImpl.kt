package com.postack.controller

import com.postack.domain.controller.CategoryController
import com.postack.domain.data.CategoryDataSource
import com.postack.domain.models.Category

class CategoryControllerImpl(
    private val categoryDataSource: CategoryDataSource
): CategoryController {
    override suspend fun addCategory(category: Category) {
        categoryDataSource.insertCategory(category)
    }

    override suspend fun getAllCategories(): List<Category> {
        return categoryDataSource.getAllCategories()
    }

    override suspend fun deleteCategory(id: String) {
        categoryDataSource.deleteCategory(id)
    }

    override suspend fun addSubCategory(categoryId: String, name: String) {
        categoryDataSource.insertSubCategory(categoryId = categoryId, name = name)
    }
}