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
            identifier = "edit-product-id"
        )
        inputField(
            classes = "row",
            label = "Name",
            labelWidth = "sm-2",
            inputWidth = "sm-10",
            named = C.PRODUCT_NAME,
            identifier = "edit-product-name"
        )
        div {
            p(classes = "topSpace") {
                +"Variant: "
            }
            select(classes = "form-select") {
                id = "select-product-variant"
                name = C.PRODUCT_SUB_CATEGORY
                onChange = "onVariantSelected(document.getElementById(\"select-product-variant\").selectedIndex)"
                option {
                    hidden = true
                    disabled = true
                    selected = true
                    value = ""
                    +"Select a variant"
                }
            }
        }
        productVariantsInputs(
            "edit-product-variant-name",
            "edit-product-variant-price",
            "edit-product-variant-weight",
            "edit-product-variant-quantity",
            "edit-product-variant-unit",
            "edit-product-variant-description",
        )
        div {
            p(classes = "topSpace") { +"Category: " }
            select(classes = "form-select") {
                id = "edit-category-selector"
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
                id = "edit-subcategory-selector"
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