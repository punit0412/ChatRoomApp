package com.example.chatroomapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class AuthViewModel: ViewModel() {
    private val userRepository: UserRepository



    init {

        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult


    fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ){
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email,password,firstName,lastName)
        }
    }

    fun login(email: String,password: String){
        viewModelScope.launch {
            _authResult.value = userRepository.login(email,password)
            Log.d("AUTH_VM", "Login result: ${_authResult.value}")
        }
    }
}