package com.postack.routes.dashboard.components.content

import kotlinx.html.*

fun DIV.advertisingOngoingContent(){
    div(classes = "tab-pane fade") {
        id = "advertising-ongoing"
        role = "tabpanel"
        attributes["aria-labelledby"] = "advertising-ongoing-tab"
        tabIndex = "0"
        +"Ongoing"
    }
}