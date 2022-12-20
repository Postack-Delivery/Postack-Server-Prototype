package com.postack.plugins

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*

fun Application.configureHTTP() {
    install(CORS) {
        allowHost("45.79.129.79")
        allowHost("postack.dev", schemes = listOf("http", "https"))
        allowHost("client-host")
        allowHost("client-host:80")
        allowHost("client-host", subDomains = listOf("en", "de", "es"))
        allowHost("client-host", schemes = listOf("http", "https"))
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Get)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.Referrer)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)
        allowNonSimpleContentTypes = true
        allowCredentials = true
        allowSameOrigin = true
        allowXHttpMethodOverride()
        anyHost()
    }


    install(CachingHeaders) {
        options { call, outgoingContent ->
            when (outgoingContent.contentType?.withoutParameters()) {
                ContentType.Text.CSS ->  CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 60))// 24 * 60 * 60 for a day
                else -> null
            }
        }
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
    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
        header(HttpHeaders.AccessControlAllowOrigin, "45.79.129.79")
    }

}
