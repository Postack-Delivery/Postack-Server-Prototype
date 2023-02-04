package com.postack.routes.dashboard.components.content

import com.postack.routes.dashboard.components.modals.tag
import kotlinx.html.*

fun DIV.advertisingCreateContent() {
    tag("advertising Create Content".uppercase())
    div(classes = "tab-pane fade") {
        id = "advertising-create"
        role = "tabpanel"
        attributes["aria-labelledby"] = "advertising-create-tab"
        tabIndex = "0"
        +"Create"
    }
}