package com.schibsted.elephant.android

import android.app.Application
import com.schibsted.elephant.android.di.NetworkModule
import com.schibsted.elephant.android.di.ViewModelModule
import com.schibsted.elephant.android.leaderboard.model.LeaderboardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class InstaActionApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InstaActionApp)
            modules(appModule, NetworkModule, ViewModelModule)
        }
    }

    private val appModule = module {
        single { LocalPreferences(get()) }
    }
}
