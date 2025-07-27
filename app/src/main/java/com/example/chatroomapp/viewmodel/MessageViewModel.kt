package com.example.chatroomapp.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Message
import com.example.chatroomapp.data.MessageRepository
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.data.User
import com.example.chatroomapp.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MessageViewModel: ViewModel() {

    private val messageRepository: MessageRepository
    private val userRepository: UserRepository

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages

    private val _roomID = MutableLiveData<String>()
//    val roomID: LiveData<String> get() = _roomID

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> get() = _currentUser

    init {
        messageRepository = MessageRepository(Injection.instance())
        userRepository = UserRepository(FirebaseAuth.getInstance(), Injection.instance())
        loadCurrentUser()

    }



    fun setRoomId(roomID: String) {
        _roomID.value = roomID
        loadMessages()

    }
    private fun loadCurrentUser(){
        viewModelScope.launch {
            val result = userRepository.getCurrentUser()
            Log.d("getCurrentUser","${result}")
            when(result){

                is Result.Success -> {

                    _currentUser.value = result.data
                    Log.d("CurrentUser","${_currentUser.value}")

                }



                is Result.Error ->{
                    // Handle Error show a snackbar

                }

            }
        }
    }



    fun loadMessages(){
        viewModelScope.launch {
            if(_roomID != null){
                messageRepository.getChatMessages(_roomID.value.toString()).collect {
                    _messages.value = it
                }
            }
        }
    }

    fun sendMessage(text: String){
        if(_currentUser.value != null){
            val message = Message(
                _currentUser.value!!.firstName,
                _currentUser.value!!.email,
                text
            )

            viewModelScope.launch {
                when(
                    messageRepository.sendMessage(_roomID.value.toString(),message)

                ) {
                    is Result.Error -> {
                        TODO()
                    }
                    is Result.Success<*> -> Unit
                }
            }
        }
    }


}