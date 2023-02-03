package com.postack.routes.dashboard.components.content

import kotlinx.html.*

fun DIV.ordersCompletedContent() {
    div(classes = "tab-pane fade") {
        id = "order-completed"
        role = "tabpanel"
        attributes["aria-labelledby"] = "order-completed-tab"
        tabIndex = "0"
        +"Completed"
    }
}