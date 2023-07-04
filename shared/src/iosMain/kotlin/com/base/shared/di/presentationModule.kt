package com.base.shared.di

import com.base.shared.presentation.main.MainViewModel
import com.base.shared.presentation.scan.ScanViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
val presentationModule = module {
    factoryOf(::MainViewModel)
    factoryOf(::ScanViewModel)
}
