package com.postack.data

import com.postack.domain.data.SupplierDataSource
import com.postack.domain.models.Supplier
import com.postack.domain.models.SupplierLocation
import io.github.reactivecircus.cache4k.Cache
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.set
import org.litote.kmongo.setTo

class SupplierDataSourceImpl(
    db: CoroutineDatabase,
    private val cache: Cache<String, List<Supplier>>
): SupplierDataSource {
    private val supplierCollection = db.getCollection<Supplier>()
    override suspend fun insertSupplier(supplier: Supplier) {
        supplierCollection.insertOne(supplier)
        cache.invalidate(ALL_SUPPLIER)
    }
    override suspend fun getAllSuppliers(): List<Supplier> {
        val cachedSuppliers = cache.get(ALL_SUPPLIER).orEmpty()
        if (cachedSuppliers.isNotEmpty()) return cachedSuppliers
        val  remoteSuppliers = supplierCollection.find().toList()
        cache.put(ALL_SUPPLIER, remoteSuppliers)
        return remoteSuppliers
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
        cache.invalidate(ALL_SUPPLIER)
    }

    override suspend fun deleteSupplier(supplier: Supplier) {
        supplierCollection.deleteOne(Supplier::id eq supplier.id)
        cache.invalidate(ALL_SUPPLIER)
    }
    companion object {
        const val ALL_SUPPLIER = "supplier"
    }
}