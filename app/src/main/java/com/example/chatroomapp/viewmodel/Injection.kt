package com.example.chatroomapp.viewmodel

import com.google.firebase.firestore.FirebaseFirestore

object Injection{
    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun instance(): FirebaseFirestore{
        return instance
    }
}