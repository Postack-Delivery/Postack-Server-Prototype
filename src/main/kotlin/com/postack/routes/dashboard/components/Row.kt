package com.postack.routes.dashboard.components

import kotlinx.html.*

fun MAIN.row(size: String = "", content: DIV.() -> Unit){
    div(classes = "row${if (size.isNotEmpty()) "-$size" else ""}") {
        content()
    }
}