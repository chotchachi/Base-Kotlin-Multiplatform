package com.base.shared.di

import com.base.shared.data.remote.KtorService
import com.base.shared.data.remote.KtorServiceImpl
import com.base.shared.data.remote.createHttpClient
import com.base.shared.data.remote.createJson
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::createJson)
    single {
        createHttpClient(
            engineFactory = OkHttp,
            json = get()
        ) {}
    }
    singleOf(::KtorServiceImpl) { bind<KtorService>() }
}