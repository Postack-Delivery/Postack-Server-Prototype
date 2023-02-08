package com.postack.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureHTTP() {

    install(CORS) {
        anyHost()
        allowHeader("*")
        allowXHttpMethodOverride()
        allowMethod(HttpMethod.Options)
        allowSameOrigin = true
    }

    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }
}

