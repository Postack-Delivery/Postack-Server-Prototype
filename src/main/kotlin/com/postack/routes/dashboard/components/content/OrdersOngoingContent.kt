package com.postack.routes.dashboard.components.content

import kotlinx.html.*

fun DIV.ordersOngoingContent() {
    div(classes = "tab-pane fade") {
        id = "order-progress"
        role = "tabpanel"
        attributes["aria-labelledby"] = "order-progress-tab"
        tabIndex = "0"
        +"Progress"
    }
}