package com.postack.routes

import com.postack.domain.controller.CategoryController
import com.postack.domain.models.Category
import com.postack.domain.models.SubCategory
import com.postack.util.C
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Paths

fun Route.categoryRoutes(categoryController: CategoryController) {
    route(C.Route.API.CATEGORIES) {
        post {
            val multipartData = call.receiveMultipart()
            val categoryBuilder = Category.Builder()
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        println("form item: ${part.name}")
                        when (part.name) {
                            C.CATEGORY_NAME -> categoryBuilder.name(part.value)
                            C.CATEGORY_COVER -> categoryBuilder.cover(part.value)
                            C.CATEGORY_SUBCATEGORY -> categoryBuilder.subCategory(
                                part.value.split(",").map { SubCategory(name = it) }
                            )
                        }
                    }

                    is PartData.FileItem -> {
                        val fileName = (part.originalFileName as String).lowercase().trim()
                        val fileBytes = part.streamProvider().readBytes()
                        categoryBuilder.cover("/static/img/$fileName")
                        File("${Paths.get("src/main/resources/static/img")}/$fileName")
                            .writeBytes(fileBytes)
                    }

                    else -> {}
                }
                part.dispose()
            }
            val category = categoryBuilder.build()
            categoryController.addCategory(category)
            call.respondText("${category.name} is uploaded to  with the image '${category.cover}")
        }
        post("/delete") {
            val multiPartData = call.receiveMultipart()
            multiPartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "ID" -> call.respond(
                                HttpStatusCode.OK,
                                categoryController.deleteCategory(part.value)
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
        post("/update") {
            val multiPartData = call.receiveMultipart()
            val categoryBuilder = Category.Builder()
            val subCategory = mutableListOf<SubCategory>()
            multiPartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "ID" -> categoryBuilder.id(part.value)
                            "name" -> {
                                categoryBuilder.name(part.value)
                            }

                            "subcategory-name1" -> {
                                if (part.value.isNotEmpty()) {
                                    subCategory.add(SubCategory(name = part.value))
                                }
                            }

                            "subcategory-name2" -> {
                                if (part.value.isNotEmpty()) {
                                    subCategory.add(SubCategory(name = part.value))
                                }                            }

                            "subcategory-name3" -> {
                                if (part.value.isNotEmpty()) {
                                    subCategory.add(SubCategory(name = part.value))
                                }                            }

                            "subcategory-name4" -> {
                                if (part.value.isNotEmpty()) {
                                    subCategory.add(SubCategory(name = part.value))
                                }                            }

                            "subcategory-name5" -> {
                                if (part.value.isNotEmpty()) {
                                    subCategory.add(SubCategory(name = part.value))
                                }                            }

                            "subcategory-name6" -> {
                                if (part.value.isNotEmpty()) {
                                    subCategory.add(SubCategory(name = part.value))
                                }                            }

                            "subcategory-name7" -> {
                                if (part.value.isNotEmpty()) {
                                    subCategory.add(SubCategory(name = part.value))
                                }                            }

                            "subcategory-name8" -> {
                                if (part.value.isNotEmpty()) {
                                    subCategory.add(SubCategory(name = part.value))
                                }                            }

                            "subcategory-name9" -> {
                                if (part.value.isNotEmpty()) {
                                    subCategory.add(SubCategory(name = part.value))
                                }
                            }
                        }
                    }

                    else -> {}
                }
            }
            val category = categoryBuilder.subCategory(subCategory).build()
            categoryController.updateCategory(category)
            call.respondText(
                "Updated ${category.id}"
            )
        }
        post("/add-subcategory") {
            val multiPartData = call.receiveMultipart()
            var categoryId = ""
            multiPartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "ID" -> categoryId = part.value
                            C.CATEGORY_NAME -> call.respond(
                                HttpStatusCode.OK,
                                categoryController.addSubCategory(
                                    categoryId = categoryId,
                                    name = part.value
                                )
                            )
                        }
                    }

                    else -> {}
                }
            }
        }

        post("/delete-subcategory") {
            val multiPartData = call.receiveMultipart()
            var categoryId = ""
            var subCategoryId = ""

            multiPartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "CategoryId" -> categoryId = part.value
                            "ID" -> subCategoryId = part.value
                            C.CATEGORY_NAME -> call.respond(
                                HttpStatusCode.OK,
                                categoryController.deleteSubcategory(categoryId = categoryId, id = subCategoryId)
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}