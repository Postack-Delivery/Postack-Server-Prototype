package com.postack

import com.postack.di.controllerModule
import com.postack.di.dataSourceModule
import com.postack.di.mainModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.postack.plugins.*
import io.ktor.network.tls.certificates.*
import io.ktor.server.plugins.doublereceive.*
import org.koin.ktor.plugin.Koin
import org.slf4j.LoggerFactory
import java.io.File

fun main() {
//    val keyStoreFile = File("build/keystore.jks")
//    val keyStore = buildKeyStore {
//        certificate("sampleAlias") {
//            password = "foobar"
//            domains = listOf("127.0.0.1", "0.0.0.0", "localhost")
//        }
//    }
//    keyStore.saveToFile(keyStoreFile, "123456")
//
//    val environment = applicationEngineEnvironment {
//        log = LoggerFactory.getLogger("ktor.application")
//        connector {
//            port = 8080
//        }
//        sslConnector(
//            keyStore = keyStore,
//            keyAlias = "sampleAlias",
//            keyStorePassword = { "123456".toCharArray() },
//            privateKeyPassword = { "foobar".toCharArray() }) {
//            port = 8443
//            keyStorePath = keyStoreFile
//        }
//        module(Application::module)
//    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
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
