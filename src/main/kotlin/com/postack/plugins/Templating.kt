package com.postack.plugins

import io.ktor.server.html.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.TextDecorationStyle
import kotlinx.css.properties.textDecoration
import java.io.File

fun Application.configureTemplating() {
    routing {
        get("/styles.css") {
            call.respondCss {
                ".hContainer" {
                    display = Display.flex
                    justifyContent = JustifyContent.lastBaseline
                    alignContent = Align.center
                }
                td {
                    textAlign = TextAlign.center
                }
                th {
                    textAlign = TextAlign.center
                }
                ul {
                    paddingLeft = 0.rem
                }
                "form-check" {
                    paddingLeft = 0.em
                }
                ".width" {
                    width = 120.px
                    marginLeft = 9.px
                    marginBottom = 25.px
                }
                ".topSpace" {
                    paddingTop = 20.px
                }
                ".top-space-sm" {
                    paddingTop = 10.px
                }
                "button.text-gray" {
                    color = Color.gray
                }
                "button.active" {
                    color = Color.black
                }

                h6 {
                    color = Color.black
                }
                 span {
                    color = Color.black
                    textDecoration(style = TextDecorationStyle.unset)
                }
                ".btn-link" {
                    textDecoration(style = TextDecorationStyle.unset)
                }
                ".btn-group-vertical" {
                    alignContent = Align.flexStart
                    verticalAlign = VerticalAlign.baseline
                }
                body {
                    backgroundColor = Color("#f8f9fa")
                }
                ".gray" {
                    backgroundColor = Color("#f8f9fa")
                }

                ".thumbnail" {
                    width = 173.px
                    height = 173.px
                }
                ".active" {
                    fontWeight = FontWeight.bold
                    color = Color.black
                }
            }
        }
    }
}

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}

