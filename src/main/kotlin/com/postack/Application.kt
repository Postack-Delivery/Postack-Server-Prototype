package com.postack

import com.postack.di.controllerModule
import com.postack.di.dataSourceModule
import com.postack.di.mainModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.postack.plugins.*
import com.postack.util.Environment
import com.postack.util.Environment.*
import com.postack.util.getProjectRoot
import io.ktor.network.tls.certificates.*
import io.ktor.server.plugins.doublereceive.*
import org.koin.ktor.plugin.Koin
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Paths

fun main() {
    val keyStoreFile = File("${getProjectRoot(PRODUCTION)}${Paths.get("src/main/resources/static")}/keystore.jks")
    val keyStore = buildKeyStore {
        certificate("postack") {
            password = "123456"
            domains = listOf("127.0.0.1", "45.79.129.79", "localhost", "postack.dev")
        }
    }
    keyStore.saveToFile(keyStoreFile, "123456")

    val environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        connector {
            port = 80
        }
        sslConnector(
            keyStore = keyStore,
            keyAlias = "postack",
            keyStorePassword = { "123456".toCharArray() },
            privateKeyPassword = { "123456".toCharArray() }) {
            port = 443
            keyStorePath = keyStoreFile
        }
        module(Application::module)
    }

    embeddedServer(Netty, environment)
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
    install(DoubleReceive) {
        cacheRawRequest = false
    }
    configureSecurity()
    configureHTTP()
    configureSockets()
    configureSerialization()
    configureTemplating()
    configureMonitoring()
    configureRouting()
}
