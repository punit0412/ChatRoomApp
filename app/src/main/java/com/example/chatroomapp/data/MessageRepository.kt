package com.example.chatroomapp.data


import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class MessageRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun sendMessage(roomID: String,
                            message: Message): Result<Unit> =
        try {

            firestore.collection("rooms").
            document(roomID).collection("messages").add(message).await()


            Result.Success(Unit)
        } catch (e: Exception){
            Result.Error(e)
        }

    suspend fun getChatMessages(roomID: String): Flow<List<Message>> = callbackFlow {
        val subscription = firestore.collection("rooms")
            .document(roomID)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    // Optional: close channel or log error
                    return@addSnapshotListener // Exits the callback early to prevent processing
                }

                querySnapshot?.let {
                    // Pushes data into Flow from a non-suspend function like a listener
                    trySend(
                        it.documents.map { doc ->
                            doc.toObject(Message::class.java)!!.copy()
                        }
                    ).isSuccess //Optional check to confirm the value was accepted into the Flow

                }
            }

        // Remove listener when flow is closed
        awaitClose {
            subscription.remove() //
        }
    }

}