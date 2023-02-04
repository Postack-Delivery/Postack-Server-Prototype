package com.postack.routes.dashboard.components.modals

import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.util.C
import kotlinx.html.*

fun MAIN.addProductVariantModal() {
    modal(
        title = "Add Product Variant",
        action = "/api/v1/products",
        identifier = "addProductVariantModal"
    ) {
        inputField(label = "Name", named = C.CATEGORY_NAME)

        div(classes = "mb-3") {
            label(classes = "col col-form-label") {
                +"lat & long"
                small("text-muted") { +"(comma separated i.e 32º,-12º)" }
            }
            div(classes = "col-sm-10") {
                input(classes = "form-control") {
                    type = InputType.text
                    name = C.CATEGORY_SUBCATEGORY
                }
            }
        }

        div(classes = "mb-3") {
            inputField(
                label = "Image",
                inputHeight = "sm-10",
                inputType = InputType.file,
                named = C.CATEGORY_COVER
            )
            div(classes = "form-check top-space-sm") {
                checkBoxInput {
                    checked = true
                    id = "imageCheckChecked"
                    +" Use current image"
                }
            }
        }
    }
}


fun DIV.tag(name: String) {
    unsafe { raw("<! -- $name -->") }
}

fun MAIN.tag(name: String) {
    unsafe { raw("<! -- $name -->") }
}