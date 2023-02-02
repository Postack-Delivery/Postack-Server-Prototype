package com.postack.di

import com.postack.data.CategoryDataSourceImpl
import com.postack.data.ProductDataSourceImpl
import com.postack.domain.data.CategoryDataSource
import com.postack.domain.data.ProductDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<ProductDataSource> {  ProductDataSourceImpl(get()) }
    single<CategoryDataSource> { CategoryDataSourceImpl(get()) }
}