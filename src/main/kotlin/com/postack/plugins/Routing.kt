package com.postack.plugins

import com.postack.domain.controller.CategoryController
import com.postack.domain.controller.ProductController
import com.postack.domain.controller.SupplierController
import com.postack.routes.api.productVariantRoutes
import com.postack.routes.api.subcategoryRoutes
import com.postack.routes.authenticationRoute
import com.postack.routes.categoryRoutes
import com.postack.routes.dashboard.dashboardRoutes
import com.postack.routes.productRoutes
import com.postack.routes.supplierRoutes
import com.postack.util.C
import com.postack.util.Environment.DEVELOPMENT
import com.postack.util.getProjectRoot
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.koin.ktor.ext.inject
import java.nio.file.Paths

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(text = "404: Page Not Found", status = status)
        }
    }

    routing {
        val productController: ProductController by inject()
        val categoryController: CategoryController by inject()
        val supplierController: SupplierController by inject()

        authenticationRoute()
        authenticate(C.ADMIN_AUTH) {
            post(C.Route.LOGIN) {
                val userName = call.principal<UserIdPrincipal>()?.name.toString()
                call.sessions.set(AdminSession(name = userName, count = 1))
                call.respondRedirect(C.Route.DASHBOARD)
            }
        }
        authenticate(C.ADMIN_SESSION) {
            dashboardRoutes(
                productController = productController,
                categoryController = categoryController,
                supplierController = supplierController
            )

        }

        productRoutes(productController = productController)
        productVariantRoutes(productController = productController)

        categoryRoutes(categoryController = categoryController)
        subcategoryRoutes(categoryController = categoryController)

        supplierRoutes(supplierController = supplierController)

        swaggerUI(
            path = C.Route.API.CURRENT_VERSION,
            swaggerFile = "${getProjectRoot(DEVELOPMENT)}${Paths.get("src/main/resources/static")}/documentation.yaml"
        ) { version = "4.15.5" }


        static(C.Route.STATIC) {
            resources("static")
        }
    }
}





