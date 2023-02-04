package com.postack.routes.dashboard.components.navigation

import com.postack.routes.components.sideBarTabs
import com.postack.routes.dashboard.components.row
import kotlinx.html.BODY
import kotlinx.html.DIV
import kotlinx.html.MAIN

fun MAIN.sideBar(content: DIV.() -> Unit){
    row {
        sideBarTabs()
        sideBarContent {
            content()
        }
    }
}