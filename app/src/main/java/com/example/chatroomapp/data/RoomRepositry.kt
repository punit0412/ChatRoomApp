package com.example.chatroomapp.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RoomRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun createRoom(
        name: String): Result<Unit> =
        try {
            val room = Room(name = name)
            firestore.collection("rooms").add(room).await()
            Result.Success(Unit)
    } catch (e: Exception){

            Result.Error(e)

    }


    //querySnapshot.documents gives list of document snapshots.
    //.map { ... } transforms each document:
    //document.toObject(Room::class.java) converts document into a Room object.
    //!! forces it to not be null (dangerous if schema mismatch).
    //.copy(id = document.id) — Firebase doesn't auto-inject the document ID into the object,
    // so you manually set it using .copy() (assuming id is a property in Room).
    suspend fun getRooms(): Result<List<Room>> =
        try {
            val querySnapshot = firestore.collection("rooms").get().await()
            val rooms = querySnapshot.documents.map {
                document -> document.toObject(Room::class.java)!!.copy(id = document.id)

            }
            Result.Success(rooms)

        } catch (e: Exception){
            Result.Error(e)
        }
}