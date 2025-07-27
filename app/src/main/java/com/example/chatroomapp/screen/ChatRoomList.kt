package com.example.chatroomapp.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatroomapp.data.Room
import com.example.chatroomapp.viewmodel.RoomViewModel

@Composable

fun ChatRoomListScreen(
    roomViewModel: RoomViewModel = viewModel(),
    onJoinedClick: (Room) -> Unit
){

    val rooms by roomViewModel.rooms.observeAsState(initial = emptyList())
    Log.d("rooms","$rooms")
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).statusBarsPadding()
    ) {
        Text("Chat Rooms",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.padding(16.dp))

        // Display list of chat rooms
        LazyColumn {
            items(rooms){

                room ->
                Log.d("LazyColumn","Inside lazy column")
                RoomItem(room,{onJoinedClick(room)})
            }


        }
        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick =
            { showDialog = true },
            modifier = Modifier.fillMaxWidth()) {

            Text("Create Room")

        }

        if (showDialog)
        {
            AlertDialog(
                onDismissRequest = {showDialog = false},
                title = {Text("Create a new room")},
                text = {
                    OutlinedTextField(
                        value = name,
                        onValueChange = {name = it},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(8.dp),

                    )
                },
                confirmButton = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = {
                            if (name.isNotBlank()){
                                Log.d("name","$name")
                                roomViewModel.createRoom(name)
                                showDialog = false
                            }
                        }) {
                            Text("Add")

                        }

                        Button(onClick = {showDialog = false}) {
                            Text("Cancel")
                        }
                    }
                }
            )
        }



    }
}

@Preview(showBackground = true)
@Composable
fun ChatRoomListScreenPreview(){
    ChatRoomListScreen {  }
}

