package com.example.chatroomapp.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatroomapp.data.Room

@Composable
fun RoomItem(room: Room,
             onJoinClicked: (Room) -> Unit
             ){
    Log.d("RoomItem","Inside RoomItem")

    Row (
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Text(room.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal)

        OutlinedButton(onClick = {
            onJoinClicked(room)
        }) {
            Text("Join")
        }
    }

}