package com.postack.routes

import com.postack.domain.controller.CategoryController
import com.postack.domain.controller.ProductController
import com.postack.domain.models.Category
import com.postack.domain.models.Product
import com.postack.domain.models.ProductVariant
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
    }
}