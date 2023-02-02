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
}