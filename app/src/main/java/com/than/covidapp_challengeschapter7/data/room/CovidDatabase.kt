package com.than.covidapp_challengeschapter7.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteEntity::class, UserEntity::class],
    version = 2
)
abstract class CovidDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun userDao(): UserDao
}