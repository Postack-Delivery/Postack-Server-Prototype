package com.postack.routes.dashboard.components.modals

import com.postack.util.C
import kotlinx.html.*

fun DIV.createCategoryModal() {
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
                        button(classes = "btn btn-dark") {
                            i(classes = "fa fa-paper-plane") {}
                            +" Submit"
                        }
                    }
                }
            }

        }
    }
}