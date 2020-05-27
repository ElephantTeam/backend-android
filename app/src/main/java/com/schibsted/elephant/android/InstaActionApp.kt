package com.schibsted.elephant.android

import android.app.Application
import com.schibsted.elephant.android.com.schibsted.elephant.android.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class InstaActionApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InstaActionApp)
            modules(appModule, NetworkModule)
        }
    }

    private val appModule = module {

    }
}