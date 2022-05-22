package com.than.covidapp_challengeschapter7.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteEntity::class],
    version = 1
)
abstract class CovidDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}