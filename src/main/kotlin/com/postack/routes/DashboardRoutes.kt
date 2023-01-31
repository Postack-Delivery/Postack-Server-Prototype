package com.postack.routes

import com.postack.domain.controller.ProductController
import com.postack.util.C
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.*
import kotlinx.html.table

fun Route.dashboardRoutes(productController: ProductController) {
    route("/dashboard") {
        get {
            val res = productController.getAllProducts(1)
            call.respondHtml {
                head {
                    meta { charset = "utf-8" }
                    meta {
                        name = "viewport"
                        content = "width=device-width, initial-scale=1"
                    }
                    unsafe {
                        +"""
                            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
                        """.trimIndent()
                    }
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                }

                body {
                    nav(classes = "navbar bg-body-tertiary") {
                        div(classes = "container-fluid") {
                            span(classes = "navbar-brand mb-0 h1") { +"Postack Dashboard" }
                        }
                    }

                    div(classes = "row") {
                        div(classes = "col-sm-2 gray") {
                            ul(classes = "nav flex-column") {
                                id = "v-pills-tab"
                                role = "tablist"
                                attributes["aria-orientation"] = "vertical"
                                li(classes = "nav-link") {
                                    h6 {
                                        strong {
                                            +"Products"
                                        }
                                    }
                                    ul {
                                        li(classes = "nav-link active") {
                                            a(classes = "text-decoration-none") {
                                                id = "product-upload-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#product-upload"
                                                attributes["aria-controls"] = "product-upload"
                                                attributes["aria-selected"] = "true"
                                                span { +"Upload" }
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a(classes = "text-decoration-none") {
                                                id = "product-inventory-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#product-inventory"
                                                attributes["aria-controls"] = "product-inventory"
                                                attributes["aria-selected"] = "false"
                                                span { +"Inventory" }
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a(classes = "text-decoration-none") {
                                                id = "product-suppliers-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#product-suppliers"
                                                attributes["aria-controls"] = "product-suppliers"
                                                attributes["aria-selected"] = "false"
                                                span { +"Suppliers" }
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a(classes = "text-decoration-none") {
                                                id = "product-categories-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#product-categories"
                                                attributes["aria-controls"] = "product-categories"
                                                attributes["aria-selected"] = "false"
                                                span { +"Categories" }
                                            }
                                        }
                                    }
                                }
                                li(classes = "nav-link") {
                                    h6 {
                                        strong {
                                            +"Orders"
                                        }
                                    }
                                    ul {
                                        li(classes = "nav-link") {
                                            a(classes = "text-decoration-none") {
                                                id = "order-completed-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#order-completed"
                                                attributes["aria-controls"] = "order-completed"
                                                attributes["aria-selected"] = "false"
                                                span { +"Completed" }
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a(classes = "text-decoration-none") {
                                                id = "order-progress-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#order-progress"
                                                attributes["aria-controls"] = "order-progress"
                                                attributes["aria-selected"] = "false"
                                                span { +"Ongoing" }
                                            }
                                        }
                                    }
                                }
                                li(classes = "nav-link") {
                                    h6 {
                                        strong {
                                            +"Advertising"
                                        }
                                    }
                                    ul {
                                        li(classes = "nav-link") {
                                            a(classes = "text-decoration-none") {
                                                id = "advertising-create-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#advertising-create"
                                                attributes["aria-controls"] = "advertising-create"
                                                attributes["aria-selected"] = "false"
                                                span { +"Create" }
                                            }
                                        }
                                        li(classes = "nav-link") {
                                            a(classes = "text-decoration-none") {
                                                id = "advertising-ongoing-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#advertising-ongoing"
                                                attributes["aria-controls"] = "advertising-ongoing"
                                                attributes["aria-selected"] = "false"
                                                span { +"Ongoing" }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        div(classes = "col-sm-8 gray") {

                            div(classes = "tab-content") {
                                id = "v-pills-tabContent"
                                div(classes = "tab-pane fade show active") {
                                    id = "product-upload"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "product-upload-tab"
                                    tabIndex = "0"

                                    div {

                                        h4 {
                                            strong {
                                                +"UPLOAD PRODUCT"
                                            }
                                        }
                                        form(
                                            action = "/api/v1/products",
                                            encType = FormEncType.multipartFormData,
                                            method = FormMethod.post,
                                            classes = "row g-3 topSpace"
                                        ) {

                                            div(classes = "mb-3 row") {
                                                label(classes = "col-sm-2 col-form-label") {
                                                    +"Name: "
                                                }
                                                div(classes = "col-sm-10") {
                                                    input(classes = "form-control") {
                                                        type = InputType.text
                                                        name = C.PRODUCT_NAME
                                                    }
                                                }
                                            }
                                            div(classes = "mb-3 row") {
                                                label(classes = "col-sm-2 col-form-label") {
                                                    +"Variant: "
                                                }
                                                div(classes = "col-sm-10") {
                                                    input(classes = "form-control") {
                                                        type = InputType.text
                                                        name = C.PRODUCT_VARIANT
                                                    }
                                                }
                                            }

                                            div(classes = "container text-left topSpace") {
                                                div(classes = "row") {
                                                    div(classes = "col") {
                                                        div(classes = "mb-3 row") {
                                                            label(classes = "col-sm-4 col-form-label") {
                                                                +"Price: "
                                                            }
                                                            div(classes = "col-sm-6") {
                                                                input(classes = "form-control") {
                                                                    type = InputType.number
                                                                    name = C.PRODUCT_PRICE
                                                                }
                                                            }
                                                        }
                                                        div(classes = "mb-3 row") {
                                                            label(classes = "col-sm-4 col-form-label") {
                                                                +"Weight: "
                                                            }
                                                            div(classes = "col-sm-6") {
                                                                input(classes = "form-control") {
                                                                    type = InputType.number
                                                                    name = C.PRODUCT_WEIGHT
                                                                }
                                                            }
                                                        }
                                                        div(classes = "mb-3 row") {
                                                            label(classes = "col-sm-4 col-form-label") {
                                                                +"Quantity: "
                                                            }
                                                            div(classes = "col-sm-6") {
                                                                input(classes = "form-control") {
                                                                    type = InputType.number
                                                                    name = C.PRODUCT_QUANTITY
                                                                }
                                                            }
                                                        }
                                                        div(classes = "mb-3 row") {
                                                            label(classes = "col-sm-4 col-form-label") {
                                                                +"UnitMeasure: "
                                                            }
                                                            div(classes = "col-sm-6") {
                                                                input(classes = "form-control") {
                                                                    type = InputType.text
                                                                    name = C.PRODUCT_UNIT_MEASURE
                                                                }
                                                            }
                                                        }
                                                        div(classes = "mb-3") {
                                                            label(classes = "col-sm-4 col-form-label") {
                                                                +"Description: "
                                                            }
                                                            div(classes = "col-sm-10") {
                                                                textArea(classes = "form-control") {
                                                                    name = C.PRODUCT_DESCRIPTION
                                                                }
                                                            }
                                                        }
                                                        div(classes = "mb-3") {
                                                            label(classes = "col-sm-4 col-form-label") {
                                                                +"Image: "
                                                            }
                                                            div(classes = "col-sm-10") {
                                                                input(classes = "form-control") {
                                                                    id = "formFile"
                                                                    type = InputType.file
                                                                    name = C.PRODUCT_IMAGE
                                                                }
                                                            }
                                                        }
                                                    }

                                                }
                                            }

                                            div {
                                                id = "SSContainer"
                                                p { +"Product Supplier: " }
                                                select(classes = "form-select") {
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
                                                select(classes = "form-select") {
                                                    id = "CSelector"
                                                    name = C.PRODUCT_CATEGORY
                                                    onChange =
                                                        "window.dispatchEvent(new CustomEvent(\"onCategoryChanged\",{ detail: { category: document.getElementById(\"CSelector\").value } }));"
                                                    option {
                                                        value = "groceries"
                                                        +"Groceries"
                                                    }
                                                    option {
                                                        value = "body"
                                                        +"Body & Bath"
                                                    }
                                                    option {
                                                        value = "beverages"
                                                        +"Beverages"
                                                    }
                                                    option {
                                                        value = "cleaning"
                                                        +"Clean Supplies"
                                                    }
                                                }


                                            }
                                            div {
                                                p {
                                                    +"Product Sub-Category: "
                                                }

                                                select(classes = "form-select") {
                                                    id = "SCSelector"
                                                    name = C.PRODUCT_SUB_CATEGORY

                                                }
                                            }

                                            submitInput(classes = "btn btn-primary width") { value = "Submit" }
                                        }
                                    }
                                }
                                div(classes = "tab-pane fade") {
                                    id = "product-inventory"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "product-inventory-tab"
                                    tabIndex = "0"
                                    div {

                                        h4 {
                                            strong {
                                                +"PRODUCT COLLECTION"
                                            }
                                        }
                                        table(classes = "table table-bordered") {
                                            thead {
                                                tr {
                                                    th {
                                                        attributes["scope"] = "row"
                                                    }
                                                    td {
                                                        h6 {
                                                            strong { +"Name" }
                                                        }
                                                    }
                                                    td {
                                                        h6 {
                                                            strong { +"Category" }
                                                        }
                                                    }
                                                    td {
                                                        h6 {
                                                            strong { +"Sub-Category" }
                                                        }
                                                    }
                                                    td {
                                                        div(classes = "row") {
                                                            div(classes = "col") {

                                                                h6(classes = "text-left") {
                                                                    strong { +"Variants" }
                                                                }

                                                            }
                                                            div(classes = "col") {


                                                                h6(classes = "text-right") {
                                                                    strong(classes = "text-muted") { +"Price" }
                                                                }

                                                            }
                                                        }

                                                    }
                                                    td {
                                                        div(classes = "col") {

                                                            h6 {
                                                                strong { +"Actions" }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            tbody(classes = "table-group-divider") {
                                                res.data.forEach { product ->
                                                    tr {
                                                        th(classes = "text-center") {
                                                            attributes["scope"] = "row"
                                                            img(classes = "center-cropped thumbnail") {
                                                                src = product.variants.first().image
                                                            }
                                                        }
                                                        td {
                                                            p { +product.name }
                                                        }
                                                        td {
                                                            p(classes = "text-capitalize") { +product.category }
                                                        }
                                                        td {
                                                            p(classes = "text-capitalize") { +product.subCategory }
                                                        }
                                                        td {
                                                            product.variants.forEachIndexed { i, productVariant ->
                                                                div(classes = "row") {
                                                                    div(classes = "col") {

                                                                        p(classes = "text-left") {
                                                                            small(classes = "text-muted") { +"${i + 1}. " }
                                                                            span { +productVariant.uniqueName }
                                                                        }
                                                                    }
                                                                    div(classes = "col") {

                                                                        p(classes = "text-muted text-right") {
                                                                            small { strong { +"ZMK" } }
                                                                            span {
                                                                                +"${productVariant.price}"
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        td {
                                                            p { +"Edit" }
                                                            p { +"Add variant" }
                                                            p { +"Delete" }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                                div(classes = "tab-pane fade") {
                                    id = "product-suppliers"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "product-suppliers-tab"
                                    tabIndex = "0"
                                    +"Suppliers"
                                }
                                div(classes = "tab-pane fade") {
                                    id = "product-categories"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "product-categories-tab"
                                    tabIndex = "0"
                                    +"Categories"
                                }
                                div(classes = "tab-pane fade") {
                                    id = "order-completed"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "order-completed-tab"
                                    tabIndex = "0"
                                    +"Completed"
                                }
                                div(classes = "tab-pane fade") {
                                    id = "order-progress"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "order-progress-tab"
                                    tabIndex = "0"
                                    +"Progress"
                                }
                                div(classes = "tab-pane fade") {
                                    id = "advertising-create"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "advertising-create-tab"
                                    tabIndex = "0"
                                    +"Create"
                                }
                                div(classes = "tab-pane fade") {
                                    id = "advertising-ongoing"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "advertising-ongoing-tab"
                                    tabIndex = "0"
                                    +"Ongoing"
                                }
                            }
                        }
                    }
                    unsafe {
                        +"""
                            <script src="https://code.jquery.com/jquery-3.6.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
                        """.trimIndent()
                    }
                    script(type = ScriptType.textJavaScript, src = "/static/js/main.js") { }
                }
            }
        }
    }
}