package com.postack.routes.dashboard.components.modals

import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import kotlinx.html.MAIN
import kotlinx.html.p

fun MAIN.deleteWarningModal(title: String, action: String, identifier: String) {
    modal(title = title, action = action, submitLabel = "Delete", identifier = identifier) {
        p { +"Are you sure you want to delete ${title.split(" ").last().lowercase()}?" }
        inputField(
            classes = "row",
            label = "ID",
            named = "CategoryId",
            labelWidth = "sm-2",
            inputWidth = "md-6",
            identifier = "parent-id"
        )

        inputField(
            classes = "row",
            label = "ID",
            named = "ID",
            labelWidth = "sm-2",
            inputWidth = "md-6",
            identifier = "item-id"
        )
    }
}