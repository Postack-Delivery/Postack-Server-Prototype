package com.postack.routes

import com.postack.domain.controller.ProductController
import com.postack.domain.models.Product
import com.postack.domain.models.ProductVariant
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Paths

fun Route.supplierRoutes() {
    route("/api/v1/suppliers") {
        post {
            val multipartData = call.receiveMultipart()
            val productVariantBuilder = ProductVariant.Builder()
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        println("form item: ${part.name}")
                    }
                    is PartData.FileItem -> {
                        val fileName = (part.originalFileName as String).lowercase().trim()
                        val fileBytes = part.streamProvider().readBytes()
                        productVariantBuilder.image("/static/img/$fileName")
                        File("${Paths.get("src/main/resources/static/img")}/$fileName")
                            .writeBytes(fileBytes)
                    }

                    else -> {}
                }
                part.dispose()
            }
        }
    }
}