package com.postack.di

import com.postack.util.C
import io.github.reactivecircus.cache4k.Cache
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import kotlin.time.Duration.Companion.hours

val mainModule = module {
    single {
        KMongo.createClient(C.CONNECTION_URL)
            .coroutine
            .getDatabase(C.DATABASE)
    }

    single<Cache<String, Any>> {
        Cache.Builder()
            .expireAfterWrite(2.hours)
            .build()
    }
}