package com.base.shared.di

import com.base.shared.presentation.main.MainViewModel
import com.base.shared.presentation.scan.ScanViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::ScanViewModel)
}