package com.postack.routes.dashboard.components.modals

import com.postack.routes.dashboard.components.flexRow
import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import kotlinx.html.*

fun MAIN.removeProductVariant() {
    modal(
        "Delete Product Variant",
    "",
    "deleteProductVariantModal",
        hasSubmitButton = false
    ) {
        inputField(
            classes = "row",
            label = "ID",
            named = "CategoryId",
            labelWidth = "sm-2",
            inputWidth = "md-6",
            identifier = "delete-variant-product-id"
        )
        for (i in 1..10) {
            flexRow(alignment = "justify-content-between", identifier = "subcategory") {
                inputField(
                    identifier = "delete-variant-name$i",
                    named = "delete-variant-name$i"
                )

                button(classes = "btn btn-danger icon-btn") {
                    id = "delete-subcategory-name$i"
                    onClick= "onDeleteSubcategory($i)"
                    attributes["type"] = "button"
                    attributes["data-bs-toggle"] = "modal"
                    attributes["data-bs-target"] = "#deleteSubcategoryModal"
                    hidden = false
                    i(classes = "fa fa-trash") {}
                }

            }
        }

    }
}