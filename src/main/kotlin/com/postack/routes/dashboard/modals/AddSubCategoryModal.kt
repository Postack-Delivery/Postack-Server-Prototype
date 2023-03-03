package com.postack.routes.dashboard.modals

import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.util.C
import kotlinx.html.MAIN

fun MAIN.addSubCategoryModal(title: String, action: String, identifier: String) {
    modal(title = title, action = action, identifier = identifier) {
        inputField(
            classes = "row",
            label = "ID",
            named = C.CATEGORY_ID,
            labelWidth = "sm-2",
            inputWidth = "md-6",
            identifier = "category-id"
        )
        inputField(
            label = "Name",
            labelWidth = "sm-4",
            inputWidth = "sm-10",
            named = C.CATEGORY_NAME
        )
    }
}