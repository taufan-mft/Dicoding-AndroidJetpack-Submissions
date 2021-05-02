package com.topanlabs.filmtopan

import android.app.Application
import com.topanlabs.filmtopan.di.Koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by taufan-mft on 5/1/2021.
 */
class MyApp : Application() {
    override fun onCreate() {

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(appModule)
        }
        super.onCreate()
    }
}