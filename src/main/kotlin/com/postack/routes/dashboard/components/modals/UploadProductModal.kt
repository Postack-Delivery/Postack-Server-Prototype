package com.postack.routes.dashboard.components.modals

import com.postack.domain.models.Category
import com.postack.util.C
import kotlinx.html.*

fun DIV.uploadProductModal(categories: List<Category>){
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

                                }

                            }
                        }
                        div(classes = "SSContainer") {
                            label(classes = "col-form-label") {
                                +"Image: "
                            }
                            div {
                                input(classes = "form-control") {
                                    id = "formFile"
                                    type = InputType.file
                                    name = C.PRODUCT_IMAGE
                                }
                            }
                        }
                        div {
                            id = "SSContainer"
                            p(classes = "topSpace") { +"Supplier: " }
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
                            p(classes = "topSpace") { +"Category: " }
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
                                        +category.name
                                    }
                                }
                            }
                        }
                        div {
                            p(classes = "topSpace") {
                                +"Sub-Category: "
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
                        submitInput(classes = "btn btn-dark") {
                            value = "Submit"
                        }
                    }
                }
            }
        }
    }
}