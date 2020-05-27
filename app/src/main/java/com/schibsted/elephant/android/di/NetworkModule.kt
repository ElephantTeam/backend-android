package com.schibsted.elephant.android.com.schibsted.elephant.android.di

import com.schibsted.elephant.android.com.schibsted.elephant.android.network.InstaActionService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val NetworkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://my-json-server.typicode.com/ElephantTeam/backend-android/")
            .build()
    }

    single { get<Retrofit>().create(InstaActionService::class.java) }

}