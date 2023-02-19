package com.postack.routes.dashboard.content

import com.postack.routes.dashboard.modals.tag
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