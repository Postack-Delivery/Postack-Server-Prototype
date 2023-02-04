package com.postack.routes

import com.postack.domain.controller.SupplierController
import com.postack.domain.models.Supplier
import com.postack.domain.models.SupplierLocation
import com.postack.util.C
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.supplierRoutes(supplierController: SupplierController) {
    route(C.Route.API.SUPPLIERS) {
        post {
            val multipartData = call.receiveMultipart()
            val supplier = buildSupplierFromData(multipartData).build()
            supplierController.addSupplier(supplier)
            call.respondRedirect(C.Route.DASHBOARD)
        }

        post("/update") {
            val multipartData = call.receiveMultipart()
            val supplier = buildSupplierFromData(multipartData).build()
            supplierController.addSupplier(supplier)
            call.respondRedirect(C.Route.DASHBOARD)
        }
    }
}

suspend fun buildSupplierFromData(multiPartData: MultiPartData): Supplier.Builder {
    val supplierBuilder = Supplier.Builder()
    val supplierLocation = SupplierLocation.Builder()

    multiPartData.forEachPart { part ->
        when (part) {
            is PartData.FormItem -> {
                when (part.name) {
                    C.SUPPLIER_NAME -> supplierBuilder.name(part.value)
                    C.SUPPLIER_LOCATION.split(" ").first() -> {
                        val location = part.value.split(",")
                        supplierLocation.lat(location.first().toFloat())
                        supplierLocation.long(location.last().toFloat())
                    }

                    C.SUPPLIER_CITY -> supplierLocation.city(part.value)
                }
            }

            else -> {}
        }
        part.dispose()
    }
    return supplierBuilder.location(supplierLocation.build())
}