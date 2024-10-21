package com.kmp

interface FirebaseDatabaseServices {
    suspend fun fetchData(path: String): String?
    suspend fun writeUserData(path: String,data:Int)
    suspend fun writeCityData(path: String,data:String)
    suspend fun fetchTraveller(path: String):Int?
}


expect object FirebaseDatabaseServiceFactory{
    fun create(): FirebaseDatabaseServices
}