package com.kmp


class Repository {

    private val services:FirebaseDatabaseServices = FirebaseDatabaseServiceFactory.create()
    suspend fun fetchUserData(): String? {
        // Define the path to your data
        val dataPath = "database/rishabh"
        // Fetch the data from Firebase
        return services.fetchData(dataPath)
    }

    suspend fun fetchTraveller(path: String):Int?{
        return services.fetchTraveller(path)
    }

    suspend fun fetchCityData(): String? {
        // Define the path to your data
        val dataPath = "database/City"
        // Fetch the data from Firebase
        return services.fetchData(dataPath)
    }
    suspend fun writeData(path: String,data: Int){
        services.writeUserData(path,data)
    }
    suspend fun writeCity(path: String,data: String){
        services.writeCityData(path,data)
    }
}