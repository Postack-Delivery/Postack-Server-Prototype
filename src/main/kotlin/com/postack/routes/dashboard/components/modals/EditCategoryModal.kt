package com.postack.routes.dashboard.components.modals

import com.postack.routes.dashboard.components.column
import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.routes.dashboard.components.flexRow
import com.postack.util.C
import kotlinx.html.*

fun MAIN.editCategoryModal() {
    modal(
        title = "Edit Category",
        action = "",
        identifier = "editCategory"
    ) {
        inputField(
            classes = "row",
            label = "ID",
            named = "ID",
            labelWidth = "sm-2",
            inputWidth = "md-6",
            identifier = "category-id"
        )
        inputField(
            named = C.CATEGORY_NAME
        )
        h6(classes = "text-muted") { +"Sub categories" }
        for (i in 1..10) {
            flexRow(alignment = "justify-content-between", identifier = "subcategory$i") {
                inputField(
                    identifier = "subcategory-name$i",
                    named = "subcategory-name$i"
                )

                button(classes = "btn btn-danger icon-btn") {
                    attributes["type"] = "button"
                    attributes["data-bs-toggle"] = "modal"
                    attributes["data-bs-target"] = "#deleteCategoryModal"
                    i(classes = "fa fa-trash") {}
                }

            }
        }
    }
}