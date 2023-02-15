package com.postack.routes.api

import com.postack.domain.controller.ProductController
import com.postack.util.C
import io.ktor.server.routing.*

fun Route.productVariantRoutes(productController: ProductController){
    route("${C.Route.API.PRODUCTS}/variants"){
        post("add") {

        }
        post("update") {

        }
        post("delete") {

        }
    }
}