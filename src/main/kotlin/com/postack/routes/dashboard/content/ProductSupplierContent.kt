package com.postack.routes.dashboard.content

import com.postack.domain.models.Supplier
import com.postack.routes.dashboard.modals.tag
import kotlinx.html.*

fun DIV.productSupplierContent(suppliers: List<Supplier>) {
    tag("Product Supplier Content".uppercase())
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
                                strong { +"City" }
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
                    suppliers.forEachIndexed { i, supplier ->
                        tr {
                            th {
                                attributes["scope"] = "row"
                                +"${i + 1}"
                            }
                            td {
                                p {
                                     +supplier.name
                                }
                            }
                            td {
                                p {
                                    +supplier.location.city
                                }
                            }
                            td {
                                div(classes = "btn-group-vertical") {
                                    role = "group"
                                    button(classes = "btn btn-link") {
                                        attributes["data-bs-toggle"] = "modal"
                                        attributes["data-bs-target"] = "#editSupplierModal"
                                        onClick="onSupplierEdit(${supplier})"

                                        p(classes = "text-md-left") {
                                            span {
                                                i(classes = "fa fa-edit") {}
                                                +" Edit"
                                            }

                                        }
                                    }

                                    button(classes = "btn btn-link") {
                                        p(classes = "text-md-left") {
                                            attributes["data-bs-toggle"] = "modal"
                                            attributes["data-bs-target"] = "#deleteSupplierModal"
                                            onClick="onDeleteItem('${supplier.id}', 'supplier')"
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
                                attributes["data-bs-target"] = "#addSupplierModal"
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