package com.postack.plugins

import com.postack.domain.controller.ProductController
import com.postack.routes.productRoutes
import com.postack.routes.productUploadRoutes
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.resources.*
import kotlinx.serialization.Serializable
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.webjars.*
import java.time.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject
import java.nio.file.Paths

fun Application.configureRouting() {

    install(Resources)

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    install(Webjars) {
        path = "/webjars" //defaults to /webjars
    }

    routing {
        val productController: ProductController by inject()
        swaggerUI(path = "/api/v1", swaggerFile = "${Paths.get("src/main/resources/static").toAbsolutePath()}/documentation.yaml") {
            version = "4.15.5"

        }
        productRoutes(productController = productController)
        productUploadRoutes()
        static("/static") {
            resources("static")
        }
        get("/webjars") {
            call.respondText("<script src='/webjars/jquery/jquery.js'></script>", ContentType.Text.Html)
        }
    }
}


@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
