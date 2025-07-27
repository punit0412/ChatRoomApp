package com.example.chatroomapp.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatroomapp.viewmodel.AuthViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun NavigationGraph(modifier: Modifier,
                    authViewModel: AuthViewModel,
                    navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Screen.SignUpScreen.route

    ) {
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(authViewModel) {
                navController.navigate(Screen.LogInScreen.route)
            }
        }

        composable(Screen.LogInScreen.route) {

            LogInScreen(authViewModel,
                onNavigateToSignUp = {navController.navigate(Screen.SignUpScreen.route)},
                ){
                Log.d("res1","Inside SignInSuccess")
                navController.navigate(Screen.ChatRoomScreen.route)
            }
        }

        composable(Screen.ChatRoomScreen.route){
            ChatRoomListScreen() {
                navController.navigate("${Screen.ChatScreen.route}/${it.id}")
            }
        }

        composable("${Screen.ChatScreen.route}/{roomID}") {
            val roomID: String = it.arguments?.getString("roomID")?:""
            ChatScreen(roomID = roomID,navController)
        }

    }

}