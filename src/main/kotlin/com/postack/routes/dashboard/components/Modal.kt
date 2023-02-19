package com.postack.routes.dashboard.components

import com.postack.routes.dashboard.modals.tag
import kotlinx.html.*

fun MAIN.modal(
    title: String,
    action: String,
    identifier: String,
    submitLabel: String = "Submit",
    method: FormMethod = FormMethod.post,
    formEncType: FormEncType = FormEncType.multipartFormData,
    hasSubmitButton: Boolean = true,
    content: DIV.() -> Unit
) {
    tag("${title.uppercase()} MODAL")
    div(classes = "modal fade") {
        id = identifier
        tabIndex = "-1"
        attributes["aria-labelledby"] = "${identifier}Label"
        attributes["aria-hidden"] = "true"
        div(classes = "modal-dialog") {
            form(
                action = action,
                encType = formEncType,
                method = method,
                classes = "row g-3 topSpace"
            ) {
                onSubmit="this.querySelectorAll('input').forEach(i => i.disabled = false)"
                div(classes = "modal-content") {
                    div(classes = "modal-header") {
                        h1(classes = "modal-title fs-5") { +title }
                    }
                    div("modal-body") {
                        content()
                    }
                    div(classes = "modal-footer") {
                        if (hasSubmitButton) {
                            submitInput(classes = "btn ${if (submitLabel == "Delete") "btn btn-danger" else "btn-dark"}") {
                                value = submitLabel
                            }
                        }
                    }
                }
            }
        }
    }
}