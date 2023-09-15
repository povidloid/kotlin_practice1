package com.example.kotlin_practice1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class viewModel : ViewModel() {
    private val login = MutableLiveData<String>()
    private val password = MutableLiveData<String>()
    private val info = MutableLiveData<String>()

    fun setLogin(text:String){
        login.value = text
    }
    fun getLogin(): LiveData<String>{
        return login
    }
    fun setPassword(text:String){
        password.value = text
    }
    fun getPassword(): LiveData<String>{
        return password
    }
    fun setInfo(text:String){
        info.value = text
    }
    fun getInfo(): LiveData<String>{
        return info
    }
}