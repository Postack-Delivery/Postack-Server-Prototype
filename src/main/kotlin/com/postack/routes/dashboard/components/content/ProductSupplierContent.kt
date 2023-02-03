package com.postack.routes.dashboard.components.content

import kotlinx.html.*

fun DIV.productSupplierContent() {
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
                                i(classes = "fa fa-plus-circle") {}
                                +" Add Supplier"
                            }
                        }
                    }
                }
            }
        }
    }
}