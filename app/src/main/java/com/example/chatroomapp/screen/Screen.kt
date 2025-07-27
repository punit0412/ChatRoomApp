package com.example.chatroomapp.screen

sealed class Screen(val route: String) {

    object LogInScreen: Screen("loginscreen")
    object SignUpScreen: Screen("signupscreen")
    object ChatRoomScreen: Screen("chatroomscreen")
    object ChatScreen: Screen("chatscreen")
}