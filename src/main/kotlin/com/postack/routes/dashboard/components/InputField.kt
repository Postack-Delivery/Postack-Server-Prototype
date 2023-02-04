package com.postack.routes.dashboard.components

import kotlinx.html.*

fun DIV.inputField(
    classes: String = "",
    identifier: String = "",
    named: String,
    label: String,
    inputType: InputType = InputType.text,
    labelHeight: String = "",
    inputHeight: String = "",
    annotation: String = "",
    isTextArea: Boolean = false
) {
    div(classes = "mb-3 $classes") {
        label(classes = "col${"-$labelHeight"} col-form-label") {
            +"$label ${if (annotation.isEmpty()) ":" else ""}"
            if (annotation.isNotEmpty()) {
                small("text-muted") { +annotation }
            }
        }
        div(classes = "col${"-$inputHeight"}") {
            if (isTextArea) {
                textArea(classes = "form-control") {
                    id = identifier
                    name = named
                }
            } else {
                input(classes = "form-control") {
                    id = identifier
                    type = inputType
                    name = named
                }
            }
        }
    }
}