package com.postack.plugins

import com.postack.domain.controller.CategoryController
import com.postack.domain.controller.ProductController
import com.postack.routes.categoryRoutes
import com.postack.routes.productRoutes
import com.postack.routes.dashboardRoutes
import com.postack.util.Environment.DEVELOPMENT
import com.postack.util.getProjectRoot
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.nio.file.Paths

fun Application.configureRouting() {

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        val productController: ProductController by inject()
        val categoryController: CategoryController by inject()

        swaggerUI(
            path = "/api/v1",
            swaggerFile = "${getProjectRoot(DEVELOPMENT)}${Paths.get("src/main/resources/static")}/documentation.yaml"
        ) { version = "4.15.5" }

        dashboardRoutes(productController = productController, categoryController = categoryController)
        productRoutes(productController = productController)
        categoryRoutes(categoryController = categoryController)

        static("/static") {
            resources("static")
        }
    }
}





