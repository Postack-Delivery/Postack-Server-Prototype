package com.postack.routes.dashboard.components

import kotlinx.html.DIV
import kotlinx.html.MAIN
import kotlinx.html.div

fun MAIN.column(classes: String = "", content: DIV.() -> Unit){
    div(classes = "col${if (classes.isNotEmpty()) "-$classes" else ""}") {
        content()
    }
}

fun DIV.column(classes: String = "", content: DIV.() -> Unit){
    div(classes = "col${if (classes.isNotEmpty()) "-$classes" else ""}") {
        content()
    }
}