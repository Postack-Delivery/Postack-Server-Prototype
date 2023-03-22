package com.postack

import com.postack.di.controllerModule
import com.postack.di.dataSourceModule
import com.postack.di.mainModule
import com.postack.plugins.*
import com.postack.util.Environment.*
import io.ktor.http.HttpHeaders.XHttpMethodOverride
import io.ktor.network.tls.certificates.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.plugins.partialcontent.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin
import org.slf4j.LoggerFactory
import java.io.File

fun main() {
    val keyStoreFile = File("./keystore.jks")

    val keyStore = buildKeyStore {
        certificate("postack") {
            password = "Postack2022"
            domains = listOf(
                "postack.cloud"
            )
        }
    }
    keyStore.saveToFile(keyStoreFile, "Postack2022")

    val environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        connector {
            port = 8080
        }
        sslConnector(
            keyStore = keyStore,
            keyAlias = "postack",
            keyStorePassword = { "Postack2022".toCharArray() },
            privateKeyPassword = { "Postack2022".toCharArray() }) {
            port = 443
            keyStorePath = keyStoreFile
        }
        module(Application::module)
    }
    embeddedServer(Jetty, environment)
        .start(wait = true)

}

fun Application.module() {
    install(Koin) {
        modules(
            listOf(
                mainModule,
                dataSourceModule,
                controllerModule
            )
        )
    }

    configureHTTP()
    install(PartialContent)
    install(DoubleReceive) {
        cacheRawRequest = false
    }
    configureSecurity()
    configureSockets()
    configureSerialization()
    configureTemplating()
    configureMonitoring()
    configureRouting()
}
