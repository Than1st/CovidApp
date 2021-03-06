package com.than.covidapp_challengeschapter7

import android.app.Application
import com.than.covidapp_challengeschapter7.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    dataStoreModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}