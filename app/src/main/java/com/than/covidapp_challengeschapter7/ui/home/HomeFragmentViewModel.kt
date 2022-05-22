package com.than.covidapp_challengeschapter7.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.than.covidapp_challengeschapter7.data.Repository
import com.than.covidapp_challengeschapter7.data.Resource
import com.than.covidapp_challengeschapter7.data.model.GetAllCountryCases
import com.than.covidapp_challengeschapter7.data.model.GetAllData
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val repository: Repository) : ViewModel() {
    private val _countryCases = MutableLiveData<Resource<List<GetAllCountryCases>>>()
    val countryCases: LiveData<Resource<List<GetAllCountryCases>>> get() = _countryCases

    private val _countryCasesById = MutableLiveData<Resource<GetAllCountryCases>>()
    val countryCasesById : LiveData<Resource<GetAllCountryCases>> get() = _countryCasesById

    private val _allDataCases = MutableLiveData<Resource<GetAllData>>()
    val allDataCases : LiveData<Resource<GetAllData>> get() = _allDataCases

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
    fun getAllCountryCasesById(country: String){
        viewModelScope.launch {
            _countryCasesById.postValue(Resource.loading())
            try {
                _countryCasesById.postValue(Resource.success(repository.getCountryCasesById(country)))
            } catch (e: Exception){
                _countryCasesById.postValue(Resource.error(e.message ?: "Error Occurred"))
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
}