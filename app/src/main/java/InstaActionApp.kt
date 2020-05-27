package com.schibsted.elephant.android


import android.app.Application
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class InstaActionApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InstaActionApp)
            modules(appModule)
        }
    }

    private val appModule = module {

    }
}