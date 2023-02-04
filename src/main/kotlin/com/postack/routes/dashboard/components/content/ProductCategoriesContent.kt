package com.postack.routes.dashboard.components.content

import com.postack.domain.models.Category
import com.postack.routes.dashboard.components.modals.tag
import kotlinx.html.*

fun DIV.productCategoriesContent(categories: List<Category>) {
    tag("Product Categories Content".uppercase())
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
                                        span { +subCategory.name }
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
                                i(classes = "fa fa-plus-circle") {}
                                +" Add Category"
                            }
                        }
                    }
                }
            }
        }
    }
}