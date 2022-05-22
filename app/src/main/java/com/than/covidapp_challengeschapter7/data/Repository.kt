package com.than.covidapp_challengeschapter7.data

import com.than.covidapp_challengeschapter7.data.room.DatabaseHelper
import com.than.covidapp_challengeschapter7.data.room.FavoriteEntity
import com.than.covidapp_challengeschapter7.data.service.ApiHelper

class Repository(private val apiHelper: ApiHelper, private val databaseHelper: DatabaseHelper) {
    // Retrofit
    suspend fun getAllCountryCases() = apiHelper.getAllCountryCases()
    suspend fun getCountryCasesById(country: String) = apiHelper.getCountryCasesById(country)
    suspend fun getAllDataCases() = apiHelper.getAllDataCases()
    
    // Database
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity) = databaseHelper.addFavorite(favoriteEntity)
    suspend fun getFavorite(id_user: Int, country_name: String): FavoriteEntity = databaseHelper.getFavorite(id_user, country_name)
    suspend fun getAllFavoriteById(id_user: Int): List<FavoriteEntity> = databaseHelper.getAllFavoriteById(id_user)
    suspend fun deleteFavorite(id_user: Int, country_name: String): Int = databaseHelper.deleteFavorite(id_user, country_name)
}