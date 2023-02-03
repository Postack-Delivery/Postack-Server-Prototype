package com.postack.routes.dashboard.components.navigation

import kotlinx.html.BODY
import kotlinx.html.div
import kotlinx.html.nav
import kotlinx.html.span

fun BODY.navBar() {
    nav(classes = "navbar bg-body-tertiary") {
        div(classes = "container-fluid") {
            span(classes = "navbar-brand mb-0 h1") { +"Postack Dashboard" }
        }
    }
}