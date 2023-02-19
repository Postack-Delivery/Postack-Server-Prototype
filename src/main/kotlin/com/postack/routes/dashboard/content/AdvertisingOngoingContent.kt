package com.postack.routes.dashboard.content

import com.postack.routes.dashboard.modals.tag
import kotlinx.html.*

fun DIV.advertisingOngoingContent(){
    tag("advertising Ongoing Content".uppercase())
    div(classes = "tab-pane fade") {
        id = "advertising-ongoing"
        role = "tabpanel"
        attributes["aria-labelledby"] = "advertising-ongoing-tab"
        tabIndex = "0"
        +"Ongoing"
    }
}