package com.postack.domain.data

import com.postack.domain.models.Supplier

interface SupplierDataSource {
    suspend fun insertSupplier(supplier: Supplier)
    suspend fun getAllSuppliers(): List<Supplier>
    suspend fun updateSupplier(supplier: Supplier)
    suspend fun deleteSupplier(supplier: Supplier)
    fun invalidateCache()

}