package com.postack.di

import com.postack.data.CategoryDataSourceImpl
import com.postack.data.ProductDataSourceImpl
import com.postack.data.SupplierDataSourceImpl
import com.postack.domain.data.CategoryDataSource
import com.postack.domain.data.ProductDataSource
import com.postack.domain.data.SupplierDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<ProductDataSource> {  ProductDataSourceImpl(db = get(), cache = get()) }
    single<CategoryDataSource> { CategoryDataSourceImpl(db = get(), cache = get()) }
    single<SupplierDataSource> { SupplierDataSourceImpl(db = get(), cache = get()) }
}