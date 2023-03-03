package com.postack.routes.dashboard.modals

import com.postack.routes.dashboard.components.flexRow
import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.util.C
import kotlinx.html.*

fun MAIN.deleteProductVariantModal() {
    modal(
        "Delete Product Variant",
    C.Route.API.DELETE_VARIANT,
    "deleteProductVariantModal",
        hasSubmitButton = false
    ) {
        for (i in 1..12) {
            flexRow(alignment = "justify-content-between", identifier = "subcategory") {
                inputField(
                    identifier = "delete-variant-name$i",
                    named = "delete-variant-name$i"
                )

                button(classes = "btn btn-danger icon-btn") {
                    id = "delete-variant-button$i"
                    onClick= "onDeleteProductVariant(${i - 1})"
                    attributes["type"] = "button"
                    attributes["data-bs-toggle"] = "modal"
                    attributes["data-bs-target"] = "#deleteProductVariantModal"
                    hidden = false
                    i(classes = "fa fa-trash") {}
                }

            }
        }

    }
}