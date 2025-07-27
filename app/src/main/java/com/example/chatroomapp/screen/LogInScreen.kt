package com.example.chatroomapp.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.viewmodel.AuthViewModel

@Composable

fun LogInScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignUp: () -> Unit,
    onSignInSuccess: () -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val result by authViewModel.authResult.observeAsState()
    Log.d("res1", "result of login is $result")

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )


        Button(
            onClick = {
                // add signup function
                authViewModel.login(email,password)

//                when(result){
//                    is Result.Success ->{
//                        Log.d("res1", "result of login is $result")
//                        onSignInSuccess()
//                    }
//
//                    is Result.Error ->{
//
//                    }
//
//                    else -> {
//
//                    }
//                }

                email = ""
                password= ""
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {

            Text("Log In")

        }

        Spacer(modifier = Modifier.padding(16.dp))

        Text("Don't have an account? Sign Up.",
            modifier = Modifier.clickable{
                onNavigateToSignUp()

            })

        when(result){
            is Result.Success ->{
                Log.d("res1", "result of login is $result")
                onSignInSuccess()
            }

            is Result.Error ->{

            }

            else -> {

            }
        }

    }

}


