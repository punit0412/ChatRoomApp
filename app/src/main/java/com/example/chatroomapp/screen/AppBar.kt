package com.example.chatroomapp.screen





import androidx.compose.foundation.background
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AppBarView(
    title: String,
    onNavBackClicked:() -> Unit

){


    TopAppBar(title = {
        Text(text = title,
            color = Color.Black,
            modifier = Modifier.padding(start = 4.dp).heightIn(max=24.dp))
    }, modifier = Modifier
        .background(Color.Yellow)
        .padding(), navigationIcon = { IconButton(onClick = {onNavBackClicked()}) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = Color.Black,
            contentDescription = null)
    } })




}
