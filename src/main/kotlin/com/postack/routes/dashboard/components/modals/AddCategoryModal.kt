package com.postack.routes.dashboard.components.modals

import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.util.C
import kotlinx.html.*

fun MAIN.addCategoryModal() {
    modal(
        title = "Create Category",
        action = C.Route.API.CATEGORIES,
        identifier = "createCategoryModal"
    ) {
        inputField(
            label = "Name",
            labelHeight = "sm-4",
            inputHeight = "sm-10",
            named = C.CATEGORY_NAME
        )

        inputField(
            label = "Sub-category",
            annotation = "(use commas i.e Breakfast, Treats)",
            inputHeight = "sm-10",
            named = C.CATEGORY_SUBCATEGORY
        )

        inputField(
            label = "Cover",
            labelHeight = "sm-4",
            inputHeight = "sm-10",
            inputType = InputType.file,
            named = C.CATEGORY_COVER
        )
    }
}
