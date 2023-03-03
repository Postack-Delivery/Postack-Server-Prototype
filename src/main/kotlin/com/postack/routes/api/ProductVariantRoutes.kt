package com.postack.routes.api

import com.postack.domain.controller.ProductController
import com.postack.domain.models.Product
import com.postack.domain.models.ProductVariant
import com.postack.util.C
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.p

fun Route.productVariantRoutes(productController: ProductController){
    route(C.Route.API.PRODUCT_VARIANT){
        post("/add") {
            val multipartData = call.receiveMultipart()
            val productVariantBuilder = ProductVariant.Builder()
            var productId = ""
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        println("form item: ${part.name}")
                        when (part.name) {
                            C.PRODUCT_ID -> productId = part.value
                            C.PRODUCT_VARIANT -> productVariantBuilder.uniqueName(part.value)
                            C.PRODUCT_PRICE -> productVariantBuilder.price(part.value.toDouble())
                            C.PRODUCT_DESCRIPTION -> productVariantBuilder.description(part.value)
                            C.PRODUCT_WEIGHT -> productVariantBuilder.weight(part.value.toDouble())
                            C.PRODUCT_QUANTITY -> productVariantBuilder.quantity(part.value.toInt())
                            C.PRODUCT_UNIT_MEASURE -> productVariantBuilder.unitMeasure(part.value)
                        }
                    }

                    else -> {}
                }
            }
            val variant = productVariantBuilder.build()
            productController.addProductVariant(productId, variant)
            call.respondText("${variant.uniqueName} is uploaded to  with the image")
        }

        post("/delete") {
            val params = call.receiveParameters()
            val variantId = params[C.PRODUCT_ID].toString()
            val productId =  params[C.DELETE_ID].toString()
            call.respond(
                HttpStatusCode.OK,
                productController.deleteProductVariant(id = productId, variantId = variantId)
            )
        }
    }
}