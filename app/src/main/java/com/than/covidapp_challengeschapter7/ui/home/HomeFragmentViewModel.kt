package com.than.covidapp_challengeschapter7.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.than.covidapp_challengeschapter7.data.Repository
import com.than.covidapp_challengeschapter7.data.Resource
import com.than.covidapp_challengeschapter7.data.model.GetAllCountryCases
import com.than.covidapp_challengeschapter7.data.model.GetAllData
import com.than.covidapp_challengeschapter7.data.room.UserEntity
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val repository: Repository) : ViewModel() {
    private val _countryCases = MutableLiveData<Resource<List<GetAllCountryCases>>>()
    val countryCases: LiveData<Resource<List<GetAllCountryCases>>> get() = _countryCases

    private val _allDataCases = MutableLiveData<Resource<GetAllData>>()
    val allDataCases : LiveData<Resource<GetAllData>> get() = _allDataCases

    private val _userPref = MutableLiveData<UserEntity>()
    val userPref : LiveData<UserEntity> get() = _userPref

    fun getAllCountryCases(){
        viewModelScope.launch {
            _countryCases.postValue(Resource.loading())
            try {
                _countryCases.postValue(Resource.success(repository.getAllCountryCases()))
            } catch (e: Exception){
                _countryCases.postValue(Resource.error(e.message ?: "Error Occurred"))
            }
        }
    }

    fun getUserPref(){
        viewModelScope.launch {
            repository.getUserPref().collect{
                _userPref.postValue(it)
            }
        }
    }

    fun getAllDataCases(){
        viewModelScope.launch {
            _allDataCases.postValue(Resource.loading())
            try {
                _allDataCases.postValue(Resource.success(repository.getAllDataCases()))
            } catch (e: Exception){
                _allDataCases.postValue(Resource.error(e.message ?: "Error Occurred"))
            }
        }
    }
    fun deleteUserPref(){
        viewModelScope.launch {
            repository.deleteUserPref()
        }
    }
}