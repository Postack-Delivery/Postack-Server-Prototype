package com.postack.controller

import com.postack.domain.controller.SupplierController
import com.postack.domain.data.SupplierDataSource
import com.postack.domain.models.Supplier

class SupplierControllerImpl(
    private val supplierDataSource: SupplierDataSource
): SupplierController {
    override suspend fun addSupplier(supplier: Supplier) {
        supplierDataSource.insertSupplier(supplier)
    }

    override suspend fun getAllSuppliers(): List<Supplier> {
        return supplierDataSource.getAllSuppliers()
    }

    override suspend fun updateSupplier(supplier: Supplier) {
        supplierDataSource.updateSupplier(supplier)
    }

    override suspend fun deleteSupplier(supplier: Supplier) {
        supplierDataSource.deleteSupplier(supplier)
    }
}