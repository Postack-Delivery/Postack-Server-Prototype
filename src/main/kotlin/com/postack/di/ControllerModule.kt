package com.postack.di

import com.postack.controller.ProductControllerImpl
import com.postack.domain.controller.ProductController
import org.koin.dsl.module

val controllerModule = module {
    single<ProductController> { ProductControllerImpl(get()) }
}