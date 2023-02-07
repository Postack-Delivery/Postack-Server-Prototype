package com.postack.routes.dashboard.components

import kotlinx.html.*

fun DIV.inputField(
    named: String,
    label: String = "",
    classes: String = "",
    labelWidth: String = "",
    inputWidth: String = "",
    annotation: String = "",
    identifier: String = "",
    isTextArea: Boolean = false,
    inputType: InputType = InputType.text
) {
    div(classes = "mb-3 $classes") {
        if (label != "ID" && label.isNotEmpty()) {
            label(classes = "col${"-$labelWidth"} col-form-label") {
                +"$label${if (annotation.isEmpty()) ":" else ""}"
                if (annotation.isNotEmpty()) {
                    small("text-muted") { +annotation }
                }
            }
        }
        div(classes = "col${"-$inputWidth"}") {
            if (isTextArea) {
                textArea(classes = "form-control") {
                    id = identifier
                    name = named
                }
            } else {
                input(classes = "form-control ${if (label == "ID") "disabled" else ""}") {
                    id = identifier
                    type = if (label == "ID") InputType.hidden else inputType
                    name = named
                    if (label == "ID") {
                        readonly = true
                    }
                    if (identifier.startsWith( "edit-subcategory")) {
                        hidden = true
                    }
                }
            }
        }
    }
}