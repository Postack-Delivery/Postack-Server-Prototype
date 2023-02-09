package com.postack.routes.dashboard.components.modals

import com.postack.domain.models.Category
import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.util.C
import kotlinx.html.*

fun MAIN.editProductModal(categories: List<Category>) {
    modal(
        title = "Edit Product",
        action = "/api/v1/products/variant",
        identifier = "editProductModal"
    ) {
        inputField(
            classes = "row",
            label = "ID",
            named = "productId",
            labelWidth = "sm-2",
            inputWidth = "md-6",
            identifier = "edit-product-product-id"
        )
        inputField(
            classes = "row",
            label = "Name",
            labelWidth = "sm-2",
            inputWidth = "sm-10",
            named = C.PRODUCT_NAME
        )
        div {
            p(classes = "topSpace") {
                +"Variant: "
            }
            select(classes = "form-select") {
                id = "SCSelector"
                name = C.PRODUCT_SUB_CATEGORY
                option {
                    hidden = true
                    disabled = true
                    selected = true
                    value = ""
                    +"Select a variant"
                }
            }
        }
        productVariantsInputs()
        div {
            p(classes = "topSpace") { +"Category: " }
            select(classes = "form-select") {
                id = "CSelector"
                name = C.PRODUCT_CATEGORY
                onChange =
                    "window.dispatchEvent(new CustomEvent(" +
                            "\"onCategoryChanged\"," +
                            "{ detail: { category: document.getElementById(\"CSelector\").value, data: $categories} }" +
                            "));"

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