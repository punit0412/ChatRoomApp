package com.example.chatroomapp.data

data class Message(
    val senderFirstName: String = "",
    val senderID: String = "",
    val text: String = "",
    val timestamp:Long = System.currentTimeMillis(),
    val isSentByCurrentUser: Boolean = false
)
