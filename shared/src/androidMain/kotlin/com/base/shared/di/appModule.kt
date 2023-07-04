package com.base.shared.di

import com.base.shared.AESDecrypt
import com.base.shared.AndroidAppCoroutineDispatchers
import com.base.shared.AppCoroutineDispatchers
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::AndroidAppCoroutineDispatchers) { bind<AppCoroutineDispatchers>() }
    singleOf(::AESDecrypt)
}