package com.postack.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.methodoverride.*

fun Application.configureHTTP() {

    install(CORS) {
        anyHost()
        allowHeader("*")
        allowXHttpMethodOverride()
        allowMethod(HttpMethod.Options)
    }

    install(DefaultHeaders) {
        header(HttpHeaders.Referrer, "no-referrer")
    }

    install(XHttpMethodOverride)

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

