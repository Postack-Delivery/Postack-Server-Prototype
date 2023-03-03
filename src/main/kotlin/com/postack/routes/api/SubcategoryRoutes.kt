package com.postack.routes.api

import com.postack.domain.controller.CategoryController
import com.postack.util.C
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.subcategoryRoutes(categoryController: CategoryController){
    route(C.Route.API.SUB_CATEGORIES){
        get {
            call.respondText("Subs")
        }
        post("/add") {
            val multiPartData = call.receiveMultipart()
            var categoryId = ""
            multiPartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            C.CATEGORY_ID -> categoryId = part.value
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

        post("/delete") {
            val params = call.receiveParameters()
            val categoryId = params[C.CATEGORY_ID].toString()
            val subCategoryId =  params[C.DELETE_ID].toString()
            call.respond(
            HttpStatusCode.OK,
            categoryController.deleteSubcategory(categoryId = categoryId, id = subCategoryId)
            )
        }
    }
}