package com.postack.routes

import com.postack.domain.controller.CategoryController
import com.postack.domain.controller.ProductController
import com.postack.util.C
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.css.th
import kotlinx.html.*
import kotlinx.html.table

fun Route.dashboardRoutes(productController: ProductController, categoryController: CategoryController) {
    route("/dashboard") {
        get {
            val products = productController.getAllProducts(1)
            val categories = categoryController.getAllCategories()

            call.respondHtml {
                head {
                    meta { charset = "utf-8" }
                    meta {
                        name = "viewport"
                        content = "width=device-width, initial-scale=1"
                    }
                    unsafe {
                        +"""
                            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                        /**
                         * @Navigation
                         */
                        div(classes = "col-sm-2 gray") {
                            ul(classes = "nav flex-column") {
                                id = "v-pills-tab"
                                role = "tablist"
                                attributes["aria-orientation"] = "vertical"
                                li(classes = "nav-link") {
                                    h6(classes = "text-muted") {
                                        strong {
                                            +"Products"
                                        }

                                    }
                                    ul {
                                        li(classes = "nav-link") {
                                            a(classes = "text-decoration-none") {
                                                id = "product-inventory-tab"
                                                role = "tab"
                                                attributes["type"] = "button"
                                                attributes["data-bs-toggle"] = "tab"
                                                attributes["data-bs-target"] = "#product-inventory"
                                                attributes["aria-controls"] = "product-inventory"
                                                attributes["aria-selected"] = "false"
                                                span {
                                                    i(classes = "fa fa-cubes") {}
                                                    +" Inventory"
                                                }
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
                                                span {
                                                    i(classes = "fa fa-users") {}
                                                    +" Suppliers"
                                                }
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
                                                span {
                                                    i(classes = "fa fa-server") {}
                                                    +" Categories"
                                                }
                                            }
                                        }
                                    }
                                }
                                li(classes = "nav-link") {
                                    h6(classes = "text-muted") {
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
                                                span {
                                                    i(classes = "fa fa-book") {}
                                                    +" Completed"
                                                }
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
                                                span {
                                                    i(classes = "fa fa-bullseye") {}
                                                    +" Ongoing"
                                                }
                                            }
                                        }
                                    }
                                }
                                li(classes = "nav-link") {
                                    h6(classes = "text-muted") {
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
                                                span {
                                                    i(classes = "fa fa-audio-description") {}
                                                    +" Create"
                                                }
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
                                                span {
                                                    i(classes = "fa fa-bullseye") {}
                                                    +" Ongoing"
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        /**
                         * @Navigation_Content
                         */
                        div(classes = "col-sm-8 gray") {

                            div(classes = "tab-content") {
                                id = "v-pills-tabContent"

                                /**
                                 * @Product_Inventory_Content
                                 */
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
                                                            strong { +"Product Name" }
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


                                                                h6(classes = "text-muted text-right") {
                                                                    small { strong { +"Price" } }
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
                                                products.data.forEach { product ->
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

                                                                        p(classes = "text-left text-truncate") {
                                                                            small(classes = "text-muted") { +"${i + 1}. " }
                                                                            span { +productVariant.uniqueName }
                                                                        }
                                                                    }
                                                                    div(classes = "col") {

                                                                        p(classes = "text-muted text-right") {
                                                                            small {
                                                                                small { strong { +"K" } }

                                                                                +String.format(
                                                                                    "%.2f",
                                                                                    productVariant.price
                                                                                )

                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        td {
                                                            div(classes = "btn-group-vertical") {
                                                                role = "group"
                                                                button(classes = "btn btn-link") {
                                                                    onClick =
                                                                        "onSubmitVariants(\"${product.name}\", \"${
                                                                            product.variants.map {
                                                                                VariantData(it.id, it.uniqueName)
                                                                            }.toString()
                                                                                .trim()

                                                                        }\")"


                                                                    p(classes = "text-md-left") {
                                                                        span {
                                                                            i(classes = "fa fa-edit") {}
                                                                            +" Edit"
                                                                        }

                                                                    }
                                                                }
                                                                button(classes = "btn btn-link") {
                                                                    p(classes = "text-md-left") {
                                                                        span {
                                                                            i(classes = "fa fa-plus-circle") {}
                                                                            +" Add variant"
                                                                        }
                                                                    }
                                                                }
                                                                button(classes = "btn btn-link") {
                                                                    p(classes = "text-md-left") {
                                                                        span {
                                                                            i(classes = "fa fa-trash") {}
                                                                            +" Delete"
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                tr {
                                                    td {
                                                        attributes["colspan"] = "5"
                                                    }
                                                    td {
                                                        button(classes = "btn btn-dark") {
                                                            attributes["data-bs-toggle"] = "modal"
                                                            attributes["data-bs-target"] = "#uploadProductModal"

                                                                i(classes = "fa fa-plus-circle") {}


                                                            +" Add Product"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                /**
                                 * @Product_Suppliers_Content
                                 */
                                div(classes = "tab-pane fade") {
                                    id = "product-suppliers"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "product-suppliers-tab"
                                    tabIndex = "0"
                                    div {

                                        h4 {
                                            strong {
                                                +"SUPPLIERS"
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
                                                            strong { +"Supplier" }
                                                        }
                                                    }
                                                    td {
                                                        h6 {
                                                            strong { +"Location" }
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
                                                tr {
                                                    td {
                                                        attributes["colspan"] = "3"
                                                    }
                                                    td {
                                                        button(classes = "btn btn-dark") {
                                                            attributes["data-bs-toggle"] = "modal"
                                                            attributes["data-bs-target"] = "#createCategoryModal"
                                                            +"Add Supplier"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                /**
                                 * @Product_Categories_Content
                                 */
                                div(classes = "tab-pane fade") {
                                    id = "product-categories"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "product-categories-tab"
                                    tabIndex = "0"
                                    div {

                                        h4 {
                                            strong {
                                                +"Categories".uppercase()
                                            }
                                        }
                                        table(classes = "table table-bordered") {
                                            thead {


                                                tr {
                                                    th {
                                                        attributes["scope"] = "row"
                                                        +"Cover"
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
                                                        div(classes = "col") {

                                                            h6 {
                                                                strong { +"Actions" }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            tbody(classes = "table-group-divider") {
                                                categories.forEach { category ->
                                                    tr {
                                                        th(classes = "text-center") {
                                                            attributes["scope"] = "row"
                                                            img(classes = "center-cropped thumbnail") {
                                                                src = category.cover
                                                            }
                                                        }
                                                        td {
                                                            p(classes = "text-capitalize") { +category.name }
                                                        }
                                                        td {
                                                            category.subCategory.forEachIndexed { i, subCategory ->
                                                                p(classes = "text-left text-truncate") {
                                                                    small(classes = "text-muted") { +"${i + 1}. " }
                                                                    span { + subCategory.name }
                                                                }
                                                            }
                                                        }
                                                        td {
                                                            div(classes = "btn-group-vertical") {
                                                                role = "group"
                                                                button(classes = "btn btn-link") {
                                                                    onClick = ""


                                                                    p(classes = "text-md-left") {
                                                                        span {
                                                                            i(classes = "fa fa-edit") {}
                                                                            +" Edit"
                                                                        }

                                                                    }
                                                                }
                                                                button(classes = "btn btn-link") {
                                                                    p(classes = "text-md-left") {
                                                                        span {
                                                                            i(classes = "fa fa-plus-circle") {}
                                                                            +" Add sub-category"
                                                                        }
                                                                    }
                                                                }
                                                                button(classes = "btn btn-link") {
                                                                    p(classes = "text-md-left") {
                                                                        span {
                                                                            i(classes = "fa fa-trash") {}
                                                                            +" Delete"
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                tr {
                                                    td {
                                                        attributes["colspan"] = "3"
                                                    }
                                                    td {
                                                        button(classes = "btn btn-dark") {
                                                            attributes["data-bs-toggle"] = "modal"
                                                            attributes["data-bs-target"] = "#createCategoryModal"
                                                            +"Add Category"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                /**
                                 * @Orders_Completed_Content
                                 */
                                div(classes = "tab-pane fade") {
                                    id = "order-completed"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "order-completed-tab"
                                    tabIndex = "0"
                                    +"Completed"
                                }
                                /**
                                 * @Orders_Ongoing_Content
                                 */
                                div(classes = "tab-pane fade") {
                                    id = "order-progress"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "order-progress-tab"
                                    tabIndex = "0"
                                    +"Progress"
                                }
                                /**
                                 * @Advertising_Create_Content
                                 */
                                div(classes = "tab-pane fade") {
                                    id = "advertising-create"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "advertising-create-tab"
                                    tabIndex = "0"
                                    +"Create"
                                }
                                /**
                                 * @Advertising_Ongoing_Content
                                 */
                                div(classes = "tab-pane fade") {
                                    id = "advertising-ongoing"
                                    role = "tabpanel"
                                    attributes["aria-labelledby"] = "advertising-ongoing-tab"
                                    tabIndex = "0"
                                    +"Ongoing"
                                }
                            }
                        }
                        /**
                         * @Modals
                         */

                        div(classes = "modal fade") {
                            id = "createCategoryModal"
                            tabIndex = "-1"
                            attributes["aria-labelledby"] = "createCategoryModalLabel"
                            attributes["aria-hidden"] = "true"
                            div(classes = "modal-dialog") {
                                form(
                                    action = "/api/v1/categories",
                                    encType = FormEncType.multipartFormData,
                                    method = FormMethod.post,
                                    classes = "row g-3 topSpace"
                                ) {

                                    div(classes = "modal-content") {
                                        div(classes = "modal-header") {
                                            h1(classes = "modal-title fs-5") { +"Create Category" }
                                        }
                                        div("modal-body") {
                                            div(classes = "mb-3") {
                                                label(classes = "col-sm-4 col-form-label") {
                                                    +"Name: "
                                                }
                                                div(classes = "col-sm-10") {
                                                    input(classes = "form-control") {
                                                        type = InputType.text
                                                        name = C.CATEGORY_NAME
                                                    }
                                                }
                                            }
                                            div(classes = "mb-3") {
                                                label(classes = "col-sm-4 col-form-label") {
                                                    +"Sub-Category: "
                                                }
                                                div(classes = "col-sm-10") {
                                                    input(classes = "form-control") {
                                                        type = InputType.text
                                                        name = C.CATEGORY_SUBCATEGORY
                                                    }
                                                }
                                            }

                                            div(classes = "mb-3") {
                                                label(classes = "col-sm-4 col-form-label") {
                                                    +"Cover: "
                                                }
                                                div(classes = "col-sm-10") {
                                                    input(classes = "form-control") {
                                                        id = "formFile"
                                                        type = InputType.file
                                                        name = C.CATEGORY_COVER
                                                    }
                                                }
                                            }


                                        }

                                        div(classes = "modal-footer") {
                                            button(classes = "btn btn-primary") {
                                                +"Submit"
                                            }
                                        }
                                    }
                                }

                            }
                        }
                        div(classes = "modal fade") {
                            id = "uploadProductModal"
                            tabIndex = "-1"
                            attributes["aria-labelledby"] = "uploadProductModal"
                            attributes["aria-hidden"] = "true"
                            div(classes = "modal-dialog") {
                                form(
                                    action = "/api/v1/products",
                                    encType = FormEncType.multipartFormData,
                                    method = FormMethod.post,
                                    classes = "row g-3 topSpace"
                                ) {
                                div(classes = "modal-content") {

                                        div(classes = "modal-header") {
                                            h1(classes = "modal-title fs-5") { +"Create Product" }
                                        }
                                        div("modal-body") {


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
                                            }
                                            div {
                                                p { +"Product Category: " }
                                                select(classes = "form-select") {
                                                    id = "CSelector"
                                                    name = C.PRODUCT_CATEGORY
                                                    onChange =
                                                        "window.dispatchEvent(new CustomEvent(\"onCategoryChanged\",{ detail: { category: document.getElementById(\"CSelector\").value, data: \"$categories\"} }));"

                                                    option {
                                                        hidden = true
                                                        disabled = true
                                                        selected = true
                                                        +"Select a category"
                                                    }
                                                    categories.forEach { category ->
                                                        option {
                                                            value = category.name.split(" ").first().lowercase()
                                                            + category.name
                                                        }
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
                                                    option {
                                                        hidden = true
                                                        disabled = true
                                                        selected = true
                                                        value = ""
                                                        +"Select a category"
                                                    }

                                                }
                                            }

                                        }

                                        div(classes = "modal-footer") {
                                            submitInput(classes = "btn btn-primary") {
                                                value = "Submit"
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                    unsafe {
                        +"""
                            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
                            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
                            <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>                      
                        """.trimIndent()
                    }
                    script(type = ScriptType.textJavaScript, src = "/static/js/main.js") { }
                }
            }
        }
    }
}

data class VariantData(
    val id: String,
    val name: String
) {
    override fun toString(): String {
        return "{-id-: -$id-, -name-: -$name-}"
    }
}