package com.kmp

import kotlinx.coroutines.tasks.await
import com.google.firebase.database.FirebaseDatabase as RealtimeDatabase

class FirebaseImplAndroid:FirebaseDatabaseServices {
    private val db = RealtimeDatabase.getInstance().reference
    override suspend fun fetchData(path: String): String? {
        return try {
                val snapshot= db.child(path).get().await()
            snapshot.getValue(String::class.java)
        }catch (e:Exception){
            null
        }
    }

    override suspend fun writeUserData(path: String, data: Int) {
        db.child(path).setValue(data).await()
    }

    override suspend fun writeCityData(path: String, data: String) {
        db.child(path).setValue(data).await()
    }

    override suspend fun fetchTraveller(path: String): Int? {
        return try {
            val snapshot= db.child(path).get().await()
            snapshot.getValue(Int::class.java)
        }catch (e:Exception){
            null
        }
    }

}

actual object FirebaseDatabaseServiceFactory{
    actual fun create(): FirebaseDatabaseServices {
        return FirebaseImplAndroid()
    }
}