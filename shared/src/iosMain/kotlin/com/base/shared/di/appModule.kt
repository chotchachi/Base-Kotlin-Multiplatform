package com.base.shared.di

import com.base.shared.AESDecrypt
import com.base.shared.AppCoroutineDispatchers
import com.base.shared.IosAppCoroutineDispatchers
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::IosAppCoroutineDispatchers) { bind<AppCoroutineDispatchers>() }
    singleOf(::AESDecrypt)
}
