package com.postack.domain.controller

import com.postack.domain.models.Category

interface CategoryController {
    suspend fun addCategory(category: Category)
    suspend fun addSubCategory(categoryId: String, name: String)
    suspend fun updateCategory(category: Category)
    suspend fun getAllCategories(): List<Category>
    suspend fun deleteCategory(id: String)
    suspend fun deleteSubcategory(categoryId: String, id: String)


}