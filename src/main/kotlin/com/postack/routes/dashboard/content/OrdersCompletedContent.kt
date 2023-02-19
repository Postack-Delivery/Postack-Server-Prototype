package com.postack.routes.dashboard.content

import com.postack.routes.dashboard.modals.tag
import kotlinx.html.*

fun DIV.ordersCompletedContent() {
    tag("Orders Completed Content".uppercase())
    div(classes = "tab-pane fade") {
        id = "order-completed"
        role = "tabpanel"
        attributes["aria-labelledby"] = "order-completed-tab"
        tabIndex = "0"
        +"Completed"
    }
}