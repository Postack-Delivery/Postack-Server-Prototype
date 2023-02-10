package com.postack.routes.dashboard.components.modals

import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.util.C
import kotlinx.html.*

fun MAIN.addProductVariantModal() {
    modal(
        title = "Add Product Variant",
        action = "/api/v1/products/variant",
        identifier = "addProductVariantModal"
    ) {
            inputField(
                classes = "row",
                label = "ID",
                named = "productId",
                labelWidth = "sm-2",
                inputWidth = "md-6",
                identifier = "add-product-variant-product-id"
            )

        productVariantsInputs()

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

fun DIV.productVariantsInputs(vararg ids: String) {
    div(classes = "container text-left topSpace") {
        div(classes = "row") {
            div(classes = "col") {
                inputField(
                    classes = "row",
                    label = "Name",
                    labelWidth = "sm-4",
                    inputWidth = "sm-6",
                    named = C.PRODUCT_PRICE,
                    inputType = InputType.text,
                    identifier =  ids.getOrNull(0).orEmpty()
                )
                inputField(
                    classes = "row",
                    label = "Price",
                    labelWidth = "sm-4",
                    inputWidth = "sm-6",
                    named = C.PRODUCT_PRICE,
                    inputType = InputType.number,
                    identifier = ids.getOrNull(1).orEmpty()
                )

                inputField(
                    classes = "row",
                    label = "Weight",
                    labelWidth = "sm-4",
                    inputWidth = "sm-6",
                    named = C.PRODUCT_WEIGHT,
                    inputType = InputType.number,
                    identifier = ids.getOrNull(2).orEmpty()
                )

                inputField(
                    classes = "row",
                    label = "Quantity",
                    labelWidth = "sm-4",
                    inputWidth = "sm-6",
                    named = C.PRODUCT_QUANTITY,
                    inputType = InputType.number,
                    identifier = ids.getOrNull(3).orEmpty()
                )
                inputField(
                    classes = "row",
                    label = "Unit-measure",
                    labelWidth = "sm-4",
                    inputWidth = "sm-6",
                    named = C.PRODUCT_UNIT_MEASURE,
                    identifier = ids.getOrNull(4).orEmpty()
                )

                inputField(
                    classes = "row",
                    label = "Description",
                    labelWidth = "sm-4",
                    inputWidth = "sm-10",
                    named = C.PRODUCT_DESCRIPTION,
                    isTextArea = true,
                    identifier = ids.getOrNull(5).orEmpty()
                )
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