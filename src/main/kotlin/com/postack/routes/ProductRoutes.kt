package com.postack.routes

import com.postack.domain.controller.ProductController
import com.postack.domain.models.Product
import io.ktor.http.*
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.img
import kotlinx.html.*
import java.io.File
import java.nio.file.Paths
import java.text.Normalizer.Form

fun Route.productRoutes(productController: ProductController) {
    route("products") {
        get {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                }
                body {
                    div {
                        h1 {
                            +"ADD PRODUCT"
                        }
                        form(
                            action = "/api/v1/products",
                            encType = FormEncType.multipartFormData,
                            method = FormMethod.post
                        ) {
                            div(classes = "hContainer") {
                                p { +"Product Name: " }
                                input {
                                    type = InputType.text
                                    name = "name"
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Price: " }
                                input {
                                    type = InputType.text
                                    name = "price"
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Description: " }
                                input {
                                    type = InputType.text
                                    name = "description"
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Weight: " }
                                input {
                                    type = InputType.number
                                    name = "weight"
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product UnitMeasure: " }
                                input {
                                    type = InputType.text
                                    name = "unit"
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Quantity: " }
                                input {
                                    type = InputType.number
                                    name = "quantity"
                                }
                            }
                            div {
                                p { +"Product Supplier: " }
                                select {
                                    name = "supplier"
                                    option {
                                        value = "town"
                                        +"Town-Center"
                                    }
                                    option {
                                        value = "soweto"
                                        +"Soweto"
                                    }

                                }
                                p { +"Product Category: " }
                                select {
                                    option { +"Groceries" }
                                }
                                p { +"Product Sub-Category: " }
                                select {
                                    option { +"Breakfast" }
                                }
                            }
                            div {
                                p { +"Product Image: " }
                                input {
                                    type = InputType.file
                                    name = "image"
                                }
                            }
                            submitInput { value = "Submit" }
                        }
                    }
                }
            }
        }
    }
    route("/api/v1/products") {
        post {
            println("*************************")
            var fileDescription = ""
            var fileName = ""

            val multipartData = call.receiveMultipart()

            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        fileDescription = part.value
                        println("form item: ${part.name}")
                    }

                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        var fileBytes = part.streamProvider().readBytes()
                        File("${Paths.get("src/main/resources/static/img")}/$fileName").writeBytes(
                            fileBytes
                        )
                    }

                    else -> {
                        println("other: $part")
                    }
                }
                part.dispose()
            }

            call.respondText("$fileDescription is uploaded to 'uploads/$fileName'")
        }
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