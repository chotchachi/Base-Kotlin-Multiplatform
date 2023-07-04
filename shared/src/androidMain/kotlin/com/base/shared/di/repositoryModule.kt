package com.base.shared.di

import com.base.shared.domain.repository.Repository
import com.base.shared.domain.repository.RepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::RepositoryImpl) { bind<Repository>() }
}