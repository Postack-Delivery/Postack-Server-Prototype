package com.postack.routes.dashboard.components

import com.postack.util.C
import kotlinx.html.DIV
import kotlinx.html.InputType
import kotlinx.html.div

fun DIV.productVariantsInputs(vararg ids: String) {
    div(classes = "container text-left topSpace") {
        div(classes = "row") {
            div(classes = "col") {
                inputField(
                    classes = "row",
                    label = "Name",
                    labelWidth = "sm-4",
                    inputWidth = "sm-6",
                    named = C.PRODUCT_VARIANT,
                    inputType = InputType.text,
                    identifier = ids.getOrNull(0).orEmpty()
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