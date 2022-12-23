package com.postack

import com.postack.di.controllerModule
import com.postack.di.dataSourceModule
import com.postack.di.mainModule
import com.postack.plugins.*
import com.postack.util.Environment.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin

fun main() {
//
//    val keyStoreFile = File("build/keystore.jks")
//    val cert = KeyStore.getInstance(KeyStore.getDefaultType())
//
//    cert.load(FileInputStream(controller.config.config.certRelativePath), controller.config.config.certKey.toCharArray())
//    val keyStore = buildKeyStore {
//        certificate("sampleAlias") {
//            cert = cert
//            password = "foobar"
//            domains = listOf(
//                "127.0.0.1",
//                "10.0.0.150",
//                "localhost",
//                "45.79.129.79",
//                "postack.dev"
//            )
//        }
//    }
//    keyStore.saveToFile(keyStoreFile, "123456")
//
//    val environment = applicationEngineEnvironment {
//        log = LoggerFactory.getLogger("ktor.application")
//        connector {
//            port = 80
//        }
//        sslConnector(
//            keyStore = keyStore,
//            keyAlias = "sampleAlias",
//            keyStorePassword = { "123456".toCharArray() },
//            privateKeyPassword = { "foobar".toCharArray() }) {
//            port = 443
//            keyStorePath = keyStoreFile
//        }
//        module(Application::module)
//    }
//    HttpsServer.createServer(Application::module)
//        .start(wait = true)
    embeddedServer(Jetty, port = 80, host = "45.79.129.79", module = Application::module)
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
