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
        invalidateCache()
    }
    override suspend fun getAllSuppliers(): List<Supplier> {
        val cachedSuppliers = cache.get(ALL_SUPPLIERS).orEmpty()
        if (cachedSuppliers.isNotEmpty()) return cachedSuppliers
        val  remoteSuppliers = supplierCollection.find().toList()
        cache.put(ALL_SUPPLIERS, remoteSuppliers)
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
        invalidateCache()
    }

    override suspend fun deleteSupplier(supplier: Supplier) {
        supplierCollection.deleteOne(Supplier::id eq supplier.id)
        invalidateCache()
    }

    override fun invalidateCache() {
        cache.invalidate(ALL_SUPPLIERS)
    }

    companion object {
        const val ALL_SUPPLIERS = "supplier"
    }
}