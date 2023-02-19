package com.postack.routes.dashboard.modals

import com.postack.routes.dashboard.components.inputField
import com.postack.routes.dashboard.components.modal
import com.postack.util.C
import kotlinx.html.*

fun MAIN.supplierModal(
    title: String,
    id: String,
    action: String
) {
    modal(
        title = title,
        action = action,
        identifier = id,
    ) {
        if (title.split(" ").first().lowercase() == "edit") {
            inputField(
                classes = "row",
                label = "ID",
                named = "ID",
                labelWidth = "sm-2",
                inputWidth = "md-6",
                identifier = "${title.split(" ").first().lowercase()}-supplier-id"
            )
        }

        inputField(
            label = C.SUPPLIER_NAME,
            named = C.SUPPLIER_NAME,
            identifier = "${title.split(" ").first().lowercase()}-supplier-name"
        )

        inputField(
            label = C.SUPPLIER_LOCATION,
            annotation = "(comma separated i.e 32º,-12º):",
            inputWidth = "sm-10",
            named = C.SUPPLIER_LOCATION.split(" ").first(),
            identifier = "${title.split(" ").first().lowercase()}-supplier-location"
        )

        inputField(
            label = C.SUPPLIER_CITY,
            named = C.SUPPLIER_CITY,
            identifier = "${title.split(" ").first().lowercase()}-supplier-city"
        )
    }
}


