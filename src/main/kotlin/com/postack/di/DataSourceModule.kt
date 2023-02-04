package com.postack.di

import com.postack.data.CategoryDataSourceImpl
import com.postack.data.ProductDataSourceImpl
import com.postack.data.SupplierDataSourceImpl
import com.postack.domain.data.CategoryDataSource
import com.postack.domain.data.ProductDataSource
import com.postack.domain.data.SupplierDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<ProductDataSource> {  ProductDataSourceImpl(get()) }
    single<CategoryDataSource> { CategoryDataSourceImpl(get()) }
    single<SupplierDataSource> { SupplierDataSourceImpl(get()) }
}