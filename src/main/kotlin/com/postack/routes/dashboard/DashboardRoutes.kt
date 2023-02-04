package com.postack.routes.dashboard

import com.postack.domain.controller.CategoryController
import com.postack.domain.controller.ProductController
import com.postack.domain.controller.SupplierController
import com.postack.plugins.AdminSession
import com.postack.routes.dashboard.components.head
import com.postack.routes.dashboard.components.content.*
import com.postack.routes.dashboard.components.modals.*
import com.postack.routes.dashboard.components.navigation.navBar
import com.postack.routes.dashboard.components.navigation.sideBar
import com.postack.util.C
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.html.*

fun Route.dashboardRoutes(
    productController: ProductController,
    categoryController: CategoryController,
    supplierController: SupplierController
) {
    route(C.Route.DASHBOARD) {
        get {
            val products = productController.getAllProducts(1)
            val categories = categoryController.getAllCategories()
            val suppliers = supplierController.getAllSuppliers()
            val adminSession = call.principal<AdminSession>()

            if (adminSession?.name?.lowercase()?.startsWith(C.ADMIN_USERNAME) == true) {
                call.sessions.set(adminSession.copy(count = adminSession.count + 1))
                call.respondHtml {
                    head(title = "Postack Dashboard")
                    body {
                        navBar()
                        main {
                            sideBar {
                                productInventoryContent(products = products)
                                productSupplierContent(suppliers = suppliers)
                                productCategoriesContent(categories = categories)
                                ordersCompletedContent()
                                ordersOngoingContent()
                                advertisingCreateContent()
                                advertisingOngoingContent()
                            }
                            addCategoryModal()
                            addProductModal(categories = categories)
                            supplierModal(
                                title = "Create Supplier",
                                id = "addSupplierModal",
                                action = C.Route.API.SUPPLIERS
                            )
                            supplierModal(
                                title = "Edit Supplier",
                                id = "editSupplierModal",
                                action = C.Route.API.UPDATE_SUPPLIER
                            )
                            addProductVariantModal()
                            deleteItemModal(
                                title = "Delete Supplier",
                                action = C.Route.API.DELETE_SUPPLIER,
                                identifier = "deleteSupplierModal"
                            )
                            deleteItemModal(
                                title = "Delete Product",
                                action = C.Route.API.DELETE_PRODUCT,
                                identifier = "deleteProductModal"
                            )
                            deleteItemModal(
                                title = "Delete Category",
                                action = C.Route.API.DELETE_PRODUCT,
                                identifier = "deleteCategoryModal"
                            )
                        }
                        unsafe {
                            +"""
                            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
                            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
                            <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>                      
                        """.trimIndent()
                        }
                        script(type = ScriptType.textJavaScript, src = "/static/js/main.js") { }
                    }
                }
            } else {
                call.respondRedirect(C.Route.LOGIN)
            }
        }
    }
}

