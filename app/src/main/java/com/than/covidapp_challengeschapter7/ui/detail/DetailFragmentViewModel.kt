package com.than.covidapp_challengeschapter7.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.than.covidapp_challengeschapter7.data.Repository
import com.than.covidapp_challengeschapter7.data.room.FavoriteEntity
import kotlinx.coroutines.launch

class DetailFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _dataFavoriteByCountry = MutableLiveData<FavoriteEntity>()
    val dataFavoriteByCountry : LiveData<FavoriteEntity> get() = _dataFavoriteByCountry


    fun insertFavorite(favoriteEntity: FavoriteEntity){
        viewModelScope.launch {
            repository.insertFavorite(favoriteEntity)
        }
    }

    fun checkFavorite(id_user: Int, country_name: String){
        viewModelScope.launch {
            _dataFavoriteByCountry.postValue(repository.getFavorite(id_user, country_name))
        }
    }

    fun deleteFavorite(id_user: Int, country_name: String){
        viewModelScope.launch {
            repository.deleteFavorite(id_user, country_name)
        }
    }
}