package com.postack.routes.dashboard.components.modals

import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import kotlinx.html.MAIN
import kotlinx.html.p

fun MAIN.deleteItemModal(title: String, action: String, identifier: String) {
    modal(title = title, action = action, submitLabel = "Delete", identifier = identifier) {
        p { +"Are you sure you want to delete ${title.split(" ").last().lowercase()}?" }
        inputField(
            classes = "row",
            label = "ID",
            named = "ID",
            labelHeight = "sm-2",
            inputHeight = "md-6",
            identifier = "item-id"
        )
    }
}