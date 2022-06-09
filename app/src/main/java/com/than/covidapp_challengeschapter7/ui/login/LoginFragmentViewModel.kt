package com.than.covidapp_challengeschapter7.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.than.covidapp_challengeschapter7.data.Repository
import com.than.covidapp_challengeschapter7.data.Resource
import com.than.covidapp_challengeschapter7.data.room.UserEntity
import kotlinx.coroutines.launch

class LoginFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _loginData : MutableLiveData<UserEntity> = MutableLiveData()
    val loginData: LiveData<UserEntity> = _loginData

    private val _userPref : MutableLiveData<UserEntity> = MutableLiveData()
    val userPref : LiveData<UserEntity> get() = _userPref


    fun getUserPref(){
        viewModelScope.launch {
            repository.getUserPref().collect{
                _userPref.postValue(it)
            }
        }
    }


    fun loginUser(username: String, password: String){
        viewModelScope.launch {
            _loginData.value = repository.loginUser(username, password)
        }
    }
}