package com.postack.routes.dashboard.components.content

import kotlinx.html.*

fun DIV.advertisingCreateContent() {
    div(classes = "tab-pane fade") {
        id = "advertising-create"
        role = "tabpanel"
        attributes["aria-labelledby"] = "advertising-create-tab"
        tabIndex = "0"
        +"Create"
    }
}