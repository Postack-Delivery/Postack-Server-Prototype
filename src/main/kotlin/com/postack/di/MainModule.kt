package com.postack.di

import com.postack.util.C
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient(C.CONNECTION_URL)
            .coroutine
            .getDatabase(C.DATABASE)
    }
}