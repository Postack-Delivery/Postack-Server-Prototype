package com.postack.data

import com.postack.domain.data.SupplierDataSource
import com.postack.domain.models.Product
import com.postack.domain.models.Supplier
import com.postack.domain.models.SupplierLocation
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.set
import org.litote.kmongo.setTo

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

    override suspend fun updateSupplier(supplier: Supplier) {
        supplierCollection.updateOne( Supplier::id eq supplier.id,
            set(
                Supplier::name setTo  supplier.name,
                Supplier::location / SupplierLocation::lat setTo supplier.location.lat,
                Supplier::location / SupplierLocation::long setTo supplier.location.long,
                Supplier::location / SupplierLocation::city setTo supplier.location.city
            )
        )
    }

    override suspend fun deleteSupplier(supplier: Supplier) {
        supplierCollection.deleteOne(Supplier::id eq supplier.id)
    }
}