package com.postack.routes.dashboard.modals

import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.routes.dashboard.components.productVariantsInputs
import com.postack.util.C
import kotlinx.html.*

fun MAIN.addProductVariantModal() {
    modal(
        title = "Add Product Variant",
        action = C.Route.API.ADD_VARIANT,
        identifier = "addProductVariantModal"
    ) {
        inputField(
            classes = "row",
            label = "ID",
            named = C.PRODUCT_ID,
            labelWidth = "sm-2",
            inputWidth = "md-6",
            identifier = "add-product-variant-product-id"
        )

        productVariantsInputs(
            "add-product-variant-name",
            "add-product-variant-price",
            "add-product-variant-weight",
            "add-product-variant-quantity",
            "add-product-variant-unit",
            "add-product-variant-description",
        )

        /* div(classes = "mb-3") {
             inputField(
                 label = "Image",
                 inputWidth = "sm-10",
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
         }*/
    }
}



fun DIV.tag(name: String) {
    unsafe { raw("<! -- $name -->") }
}

fun MAIN.tag(name: String) {
    unsafe { raw("<! -- $name -->") }
}