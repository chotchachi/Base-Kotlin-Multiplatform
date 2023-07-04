package com.base.kmm.android

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.base.kmm.android.di.viewModelModule
import com.base.shared.initKoin
import com.base.shared.setupNapier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MyApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        setupNapier()
        initKoin {
            androidContext(this@MyApplication)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.INFO)
            modules(viewModelModule)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}