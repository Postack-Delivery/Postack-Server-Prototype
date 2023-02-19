package com.postack.routes.dashboard.modals

import com.postack.routes.dashboard.components.column
import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.routes.dashboard.components.flexRow
import com.postack.util.C
import kotlinx.html.*

fun MAIN.editCategoryModal() {
    modal(
        title = "Edit Category",
        action = C.Route.API.UPDATE_CATEGORY,
        formEncType = FormEncType.applicationXWwwFormUrlEncoded,
        identifier = "editCategory"
    ) {
        inputField(
            classes = "row",
            label = "ID",
            named = "category-Id",
            labelWidth = "sm-2",
            inputWidth = "md-6",
            identifier = "edit-category-id"
        )
        inputField(
            identifier = "edit-category-name",
            named = C.CATEGORY_NAME
        )
        h6(classes = "text-muted") { +"Sub categories" }
        for (i in 1..10) {
            flexRow(alignment = "justify-content-between", identifier = "subcategory") {
                inputField(
                    identifier = "edit-subcategory-name$i",
                    named = "edit-subcategory-name$i"
                )

                button(classes = "btn btn-danger icon-btn") {
                    id = "delete-subcategory-name$i"
                    onClick= "onDeleteSubcategory($i)"
                    attributes["type"] = "button"
                    attributes["data-bs-toggle"] = "modal"
                    attributes["data-bs-target"] = "#deleteSubcategoryModal"
                    hidden = true
                    i(classes = "fa fa-trash") {}
                }

            }
        }
    }
}