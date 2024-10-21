package com.kmp

class DatabaseRepo(private val firebaseDatabase: FirebaseDatabaseServices) {
    suspend fun getUsers(key:String):String?{
        return firebaseDatabase.fetchData(key)
    }
}