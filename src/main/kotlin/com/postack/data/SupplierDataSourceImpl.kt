package com.postack.data

import com.postack.domain.data.SupplierDataSource
import com.postack.domain.models.Supplier

class SupplierDataSourceImpl(

): SupplierDataSource {
    override suspend fun insertSupplier(supplier: Supplier) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSuppliers(): List<Supplier> {
        TODO("Not yet implemented")
    }

}