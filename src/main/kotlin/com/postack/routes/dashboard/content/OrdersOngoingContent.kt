package com.postack.routes.dashboard.content

import com.postack.routes.dashboard.modals.tag
import kotlinx.html.*

fun DIV.ordersOngoingContent() {
    tag("Orders Ongoing Content".uppercase())
    div(classes = "tab-pane fade") {
        id = "order-progress"
        role = "tabpanel"
        attributes["aria-labelledby"] = "order-progress-tab"
        tabIndex = "0"
        +"Progress"
    }
}