package com.postack.routes

import com.postack.domain.controller.ProductController
import com.postack.domain.models.Product
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
    route("/api/v1/products") {
        post {
            val multipartData = call.receiveMultipart()
            val productBuilder = Product.Builder()
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        println("form item: ${part.name}")
                        when (part.name) {
                            C.PRODUCT_NAME -> productBuilder.name(part.value)
                            C.PRODUCT_PRICE -> productBuilder.price(part.value.toDouble())
                            C.PRODUCT_DESCRIPTION -> productBuilder.description(part.value)
                            C.PRODUCT_WEIGHT -> productBuilder.weight(part.value.toDouble())
                            C.PRODUCT_QUANTITY -> productBuilder.quantity(part.value.toInt())
                            C.PRODUCT_UNIT_MEASURE -> productBuilder.unitMeasure(part.value)
                            C.PRODUCT_SUPPLIER -> productBuilder.supplier(part.value)
                            C.PRODUCT_CATEGORY -> productBuilder.category(part.value)
                            C.PRODUCT_SUB_CATEGORY -> productBuilder.subCategory(part.value)
                        }
                    }

                    is PartData.FileItem -> {
                        val fileName = (part.originalFileName as String).lowercase().trim()
                        val fileBytes = part.streamProvider().readBytes()
                        productBuilder.image("10.0.0.150:8484/static/img/$fileName")
                        File("${Paths.get("src/main/resources/static/img")}/$fileName")
                            .writeBytes(fileBytes)
                    }
                    else -> {}
                }
                part.dispose()
            }
            val product = productBuilder.build()
            productController.addProduct(product)
            call.respondText("${product.name} is uploaded to  with the image '${product.image}")
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

        get("/{subCategory}") {
            val subCategory = call.parameters["subCategory"].toString()
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            call.respond(
                HttpStatusCode.OK,
                productController.getProductsBySubCategory(subCategory, page)
            )
        }

        get("/search/{searchTerm}") {
            val searchTerm = call.parameters["searchTerm"].toString()
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

        delete("/{id}") {
            val id = call.parameters["id"].toString()
            call.respond(
                HttpStatusCode.OK,
                productController.deleteProduct(id)
            )
        }

    }
}