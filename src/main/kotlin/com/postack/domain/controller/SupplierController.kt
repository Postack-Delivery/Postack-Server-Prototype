package com.postack.domain.controller

import com.postack.domain.models.Supplier

interface SupplierController {
    suspend fun addSupplier(supplier: Supplier)
    suspend fun getAllSuppliers(): List<Supplier>
    suspend fun updateSupplier(supplier: Supplier)
    suspend fun deleteSupplier(supplier: Supplier)
}