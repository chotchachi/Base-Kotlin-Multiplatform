package com.base.shared.di

import com.base.shared.data.local.RealmPersistence
import com.base.shared.data.local.RealmPersistenceImpl
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::createRealmDatabase)
    singleOf(::RealmPersistenceImpl) { bind<RealmPersistence>() }
}

private fun createRealmDatabase(): Realm {
    val configuration = RealmConfiguration.create(
        schema = setOf(

        )
    )

    return Realm.open(configuration = configuration)
}