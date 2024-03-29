package com.postack.routes

import com.postack.domain.controller.ProductController
import com.postack.domain.models.Product
import com.postack.domain.models.ProductVariant
import com.postack.util.C
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Paths

fun Route.productRoutes(productController: ProductController) {
    route(C.Route.API.PRODUCTS) {
        post {
            val multipartData = call.receiveMultipart()
            val productBuilder = Product.Builder()
            val productVariantBuilder = ProductVariant.Builder()
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        println("form item: ${part.name}")
                        when (part.name) {
                            C.PRODUCT_NAME -> productBuilder.name(part.value)
                            C.PRODUCT_PRICE -> productVariantBuilder.price(part.value.toDouble())
                            C.PRODUCT_DESCRIPTION -> productVariantBuilder.description(part.value)
                            C.PRODUCT_WEIGHT -> productVariantBuilder.weight(part.value.toDouble())
                            C.PRODUCT_QUANTITY -> productVariantBuilder.quantity(part.value.toInt())
                            C.PRODUCT_UNIT_MEASURE -> productVariantBuilder.unitMeasure(part.value)
                            C.PRODUCT_VARIANT -> productVariantBuilder.uniqueName(part.value)
                            C.PRODUCT_SUPPLIER -> productBuilder.supplier(part.value)
                            C.PRODUCT_CATEGORY -> productBuilder.category(part.value)
                            C.PRODUCT_SUB_CATEGORY -> productBuilder.subCategory(part.value)
                        }
                    }

                    is PartData.FileItem -> {
                        val fileName = (part.originalFileName as String).lowercase().trim()
                        val fileBytes = part.streamProvider().readBytes()
                        productVariantBuilder.image("/static/img/$fileName")
                        File("${Paths.get("src/main/resources/static/img")}/$fileName")
                            .writeBytes(fileBytes)
                    }
                    else -> {}
                }
                part.dispose()
            }
            productBuilder.variants(listOf(productVariantBuilder.build()))
            val product = productBuilder.build()
            productController.addProduct(product)
            call.respondText("${product.name} is uploaded to  with the image '${product.variants.first().image}")
        }


        get {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            call.respond(
                HttpStatusCode.OK,
                productController.getAllProducts(page)
            )
        }

        get("recommended/{category}") {
            val category = call.parameters["category"].toString()
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            call.respond(
                HttpStatusCode.OK,
                productController.getCategoryRecommendedProducts(category, page)
            )
        }

        get("/{sub-category}") {
            val subCategory = call.parameters["sub-category"].toString()
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            call.respond(
                HttpStatusCode.OK,
                productController.getProductsBySubCategory(subCategory, page)
            )
        }

        get("/search/{search-term}") {
            val searchTerm = call.parameters["search-term"].toString()
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            call.respond(
                HttpStatusCode.OK,
                productController.getProductsBySearchTerm(searchTerm, page)
            )
        }

        put {
            val product = call.receive<Product>()
            call.respond(
                HttpStatusCode.OK,
                productController.updateProduct(product)
            )
        }

        post("/delete") {
            val multiPartData = call.receiveMultipart()
            multiPartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "ID" -> call.respond(
                                HttpStatusCode.OK,
                                productController.deleteProduct(part.value)
                            )
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}