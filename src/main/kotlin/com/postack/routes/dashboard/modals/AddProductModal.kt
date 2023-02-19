package com.postack.routes.dashboard.modals

import com.postack.domain.models.Category
import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.util.C
import kotlinx.html.*

fun MAIN.addProductModal(categories: List<Category>) {
    modal(
        title = "Create Product",
        action = C.Route.API.PRODUCTS,
        identifier = "uploadProductModal"
    ) {
        inputField(
            classes = "row",
            label = "Name",
            labelWidth = "sm-2",
            inputWidth = "sm-10",
            named = C.PRODUCT_NAME
        )
        inputField(
            classes = "row",
            label = "Variant",
            labelWidth = "sm-2",
            inputWidth = "sm-10",
            named = C.PRODUCT_VARIANT
        )

        div(classes = "container text-left topSpace") {
            div(classes = "row") {
                div(classes = "col") {
                    inputField(
                        classes = "row",
                        label = "Price",
                        labelWidth = "sm-4",
                        inputWidth = "sm-6",
                        named = C.PRODUCT_PRICE,
                        inputType = InputType.number
                    )

                    inputField(
                        classes = "row",
                        label = "Weight",
                        labelWidth = "sm-4",
                        inputWidth = "sm-6",
                        named = C.PRODUCT_WEIGHT,
                        inputType = InputType.number
                    )

                    inputField(
                        classes = "row",
                        label = "Quantity",
                        labelWidth = "sm-4",
                        inputWidth = "sm-6",
                        named = C.PRODUCT_QUANTITY,
                        inputType = InputType.number
                    )
                    inputField(
                        classes = "row",
                        label = "Unit-measure",
                        labelWidth = "sm-4",
                        inputWidth = "sm-6",
                        named = C.PRODUCT_UNIT_MEASURE,
                    )

                    inputField(
                        classes = "row",
                        label = "Description",
                        labelWidth = "sm-4",
                        inputWidth = "sm-10",
                        named = C.PRODUCT_DESCRIPTION,
                        isTextArea = true
                    )
                }
            }
        }

        div(classes = "SSContainer") {
            inputField(
                label = "Image",
                named = C.PRODUCT_IMAGE,
                inputType = InputType.file
            )
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
                    """window.dispatchEvent(new CustomEvent(
                            "onCategoryChanged", { detail: { 
                            category: document.getElementById("CSelector").value, 
                            data: $categories,
                            id: 'SCSelector'
                            }   
                         }
                       ));
                    """

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
}
