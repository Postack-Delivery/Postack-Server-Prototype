package com.postack.plugins

import com.postack.util.C
import io.ktor.server.auth.*
import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.response.*

data class AdminSession(val name: String, val count: Int) : Principal

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<AdminSession>(C.ADMIN_USERNAME) {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60 * 60 * 2
        }
    }

    install(Authentication) {
        form(C.ADMIN_AUTH) {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                if (credentials.name.lowercase() == C.ADMIN_USERNAME && credentials.password == C.ADMIN_PASSWORD) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
        session<AdminSession>(C.ADMIN_SESSION) {
            validate { session ->
                if(session.name.lowercase().startsWith(C.ADMIN_USERNAME)) {
                    session
                } else {
                    null
                }
            }
            challenge {
                call.respondRedirect(C.Route.LOGIN)
            }
        }
    }
}
