package com.postack.di

import com.postack.controller.CategoryControllerImpl
import com.postack.controller.ProductControllerImpl
import com.postack.controller.SupplierControllerImpl
import com.postack.domain.controller.CategoryController
import com.postack.domain.controller.ProductController
import com.postack.domain.controller.SupplierController
import org.koin.dsl.module

val controllerModule = module {
    single<ProductController> { ProductControllerImpl(get()) }
    single<CategoryController> { CategoryControllerImpl(get()) }
    single<SupplierController> { SupplierControllerImpl(get()) }
}