package com.than.covidapp_challengeschapter7.di

import androidx.room.Room
import com.than.covidapp_challengeschapter7.data.room.CovidDatabase
import com.than.covidapp_challengeschapter7.data.room.DatabaseHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            CovidDatabase::class.java,
            "covidDatabase"
        ).build()
    }
    single {
        get<CovidDatabase>().favoriteDao()
    }
    singleOf(::DatabaseHelper)
}