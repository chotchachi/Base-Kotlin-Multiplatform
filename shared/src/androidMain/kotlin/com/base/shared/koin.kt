package com.base.shared

import com.base.shared.di.appModule
import com.base.shared.di.databaseModule
import com.base.shared.di.networkModule
import com.base.shared.di.presentationModule
import com.base.shared.di.repositoryModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
    appDeclaration()
    modules(
        listOf(
            appModule,
            networkModule,
            databaseModule,
            repositoryModule,
            presentationModule
        )
    )
}