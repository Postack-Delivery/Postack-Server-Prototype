package com.postack.routes.dashboard.components.content

import com.postack.domain.models.ProductResponse
import com.postack.routes.dashboard.components.modals.tag
import kotlinx.html.*

fun DIV.productInventoryContent(products: ProductResponse) {
    tag("Product Inventory Content".uppercase())
    div(classes = "tab-pane fade show active") {
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
                                        attributes["data-bs-toggle"] = "modal"
                                        attributes["data-bs-target"] = "#editProductModal"
                                        onClick = "onEditProduct(${product})"
                                        p(classes = "text-md-left") {
                                            span {
                                                i(classes = "fa fa-edit") {}
                                                +" Edit"
                                            }

                                        }
                                    }
                                    button(classes = "btn btn-link") {
                                        attributes["data-bs-toggle"] = "modal"
                                        attributes["data-bs-target"] = "#addProductVariantModal"
                                        onClick="onAddProductVariant('${product.id}')"
                                        p(classes = "text-md-left") {
                                            span {
                                                i(classes = "fa fa-plus-circle") {}
                                                +" Add variant"
                                            }
                                        }
                                    }
                                    button(classes = "btn btn-link") {
                                        attributes["data-bs-toggle"] = "modal"
                                        attributes["data-bs-target"] = "#deleteProductVariantModal"
                                        onClick="onAddProductVariant('${product.id}')"
                                        p(classes = "text-md-left") {
                                            span {
                                                i(classes = "fa fa-minus-circle") {}
                                                +" Remove variant"
                                            }
                                        }
                                    }
                                    button(classes = "btn btn-link") {
                                        attributes["data-bs-toggle"] = "modal"
                                        attributes["data-bs-target"] = "#deleteProductModal"
                                        onClick="onDeleteItem('${product.id}', 'product')"
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
}