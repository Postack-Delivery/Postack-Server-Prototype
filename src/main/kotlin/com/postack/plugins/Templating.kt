package com.postack.plugins

import io.ktor.server.html.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {
    routing {
        get("/styles.css") {
            call.respondCss {
                ".hContainer" {
                    display = Display.flex
                    justifyContent = JustifyContent.lastBaseline
                    alignContent = Align.center
                }
                input {
                    height = 30.px
                }
                ul {
                    paddingLeft = 0.rem
                }
            }
        }
    }
}

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}

