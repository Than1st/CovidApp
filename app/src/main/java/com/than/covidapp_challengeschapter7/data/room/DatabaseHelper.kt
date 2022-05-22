package com.than.covidapp_challengeschapter7.data.room

class DatabaseHelper(private val favoriteDao: FavoriteDao) {
    suspend fun addFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.addFavorite(favoriteEntity)
    suspend fun getFavorite(id_user: Int, country_name: String): FavoriteEntity = favoriteDao.getFavorite(id_user, country_name)
    suspend fun getAllFavoriteById(id_user: Int): List<FavoriteEntity> = favoriteDao.getAllFavoriteById(id_user)
    suspend fun deleteFavorite(id_user: Int, country_name: String): Int = favoriteDao.deleteFavorite(id_user, country_name)
}