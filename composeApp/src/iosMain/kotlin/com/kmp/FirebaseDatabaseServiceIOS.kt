package com.kmp

class FirebaseDatabaseServiceIOS: FirebaseDatabaseServices {
    override suspend fun fetchData(path: String): String? {
        return null
    }

}

actual object FirebaseDatabaseServiceFactory{
    actual fun create():FirebaseDatabaseServices{
        return FirebaseDatabaseServiceIOS()
    }
}