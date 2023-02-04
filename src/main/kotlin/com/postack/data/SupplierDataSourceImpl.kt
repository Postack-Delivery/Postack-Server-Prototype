package com.postack.data

import com.postack.domain.data.SupplierDataSource
import com.postack.domain.models.Product
import com.postack.domain.models.Supplier
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne

class SupplierDataSourceImpl(
    db: CoroutineDatabase
): SupplierDataSource {
    private val supplierCollection = db.getCollection<Supplier>()
    override suspend fun insertSupplier(supplier: Supplier) {
        supplierCollection.insertOne(supplier)
    }
    override suspend fun getAllSuppliers(): List<Supplier> {
        return supplierCollection.find().toList()
    }

}