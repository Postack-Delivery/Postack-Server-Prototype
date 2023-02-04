package com.postack.routes.dashboard.components.navigation

import kotlinx.html.BODY
import kotlinx.html.DIV
import kotlinx.html.div
import kotlinx.html.id

fun DIV.sideBarContent(content: DIV.() -> Unit){
    div(classes = "col-sm-8 gray") {
        div(classes = "tab-content") {
            id = "v-pills-tabContent"
            content()
        }
    }
}
