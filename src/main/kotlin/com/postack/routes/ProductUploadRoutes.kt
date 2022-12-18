package com.postack.routes

import com.postack.util.C
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Route.productUploadRoutes() {
    route("/products/upload") {
        get {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                }
                body {
                    div {
                        h1 {
                            +"UPLOAD PRODUCT"
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
                                    name = C.PRODUCT_NAME
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Price: " }
                                input {
                                    type = InputType.text
                                    name = C.PRODUCT_PRICE
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Description: " }
                                input {
                                    type = InputType.text
                                    name = C.PRODUCT_DESCRIPTION
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Weight: " }
                                input {
                                    type = InputType.number
                                    name = C.PRODUCT_WEIGHT
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product UnitMeasure: " }
                                input {
                                    type = InputType.text
                                    name = C.PRODUCT_UNIT_MEASURE
                                }
                            }
                            div(classes = "hContainer") {
                                p { +"Product Quantity: " }
                                input {
                                    type = InputType.number
                                    name = C.PRODUCT_QUANTITY
                                }
                            }
                            div {
                                p { +"Product Supplier: " }
                                select {
                                    name = C.PRODUCT_SUPPLIER
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
                                    name = C.PRODUCT_CATEGORY
                                    option {
                                        value = "groceries"
                                        +"Groceries"
                                    }
                                }
                                p { +"Product Sub-Category: " }
                                select {
                                    name = C.PRODUCT_SUB_CATEGORY
                                    option {
                                        value = "breakfast"
                                        +"Breakfast"
                                    }
                                }
                            }
                            div {
                                p { +"Product Image: " }
                                input {
                                    type = InputType.file
                                    name = C.PRODUCT_IMAGE
                                }
                            }
                            submitInput { value = "Submit" }
                        }
                    }
                }
            }
        }
    }
}