package com.postack.routes.dashboard.components

import kotlinx.html.*

fun MAIN.flexRow(classes: String = "", content: DIV.() -> Unit){
    div(classes = "row${if (classes.isNotEmpty()) "-$classes" else ""}") {
        content()
    }
}

fun DIV.flexRow(alignment: String = "", identifier: String = "", content: DIV.() -> Unit){
    div(classes = "d-flex ${if (alignment.isNotEmpty()) "$alignment" else ""}") {
        id = identifier
        content()
    }
}