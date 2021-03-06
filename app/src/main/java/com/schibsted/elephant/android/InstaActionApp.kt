package com.schibsted.elephant.android

import android.app.Application
import com.schibsted.elephant.android.di.NetworkModule
import com.schibsted.elephant.android.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class InstaActionApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InstaActionApp)
            modules(appModule, NetworkModule, ViewModelModule)
        }
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    private val appModule = module {
        single { LocalPreferences(get()) }
    }
}
