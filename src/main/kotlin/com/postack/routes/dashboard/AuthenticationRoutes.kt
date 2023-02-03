package com.postack.routes

import com.postack.routes.dashboard.components.postackPageHeader
import com.postack.util.C
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.css.div
import kotlinx.css.form
import kotlinx.html.*
import java.awt.Label


fun Route.authenticationRoute() {
    get(C.Route.LOGIN) {
        call.respondHtml {
            postackPageHeader("Postack Dashboard Login")
            body {
                div(classes = "d-flex justify-content-center align-items-center vh-100") {
                    div(classes = "card text-bg-light mb-3") {
                        attributes["style"] = "max-width: 20rem"
                        h5(classes = "card-header") { +"Postack Dashboard" }
                        div(classes = "card-body") {
                            form(
                                classes = "row gy-2 gx-3 align-items-center",
                                action = C.Route.LOGIN,
                                encType = FormEncType.applicationXWwwFormUrlEncoded,
                                method = FormMethod.post
                            ) {
                                div(classes = "input-group-text") {
                                    +"@"
                                    textInput(classes = "form-control", name = "username") {
                                        attributes["placeholder"] = "Username"
                                    }
                                }
                                div(classes = "input-group-text") {
                                    passwordInput(classes = "form-control", name = "password") {
                                        attributes["placeholder"] = "Password"
                                    }
                                }
                                submitInput(classes = "btn btn-dark") { value = "Login" }
                            }
                        }
                    }
                }
            }
        }
    }
}