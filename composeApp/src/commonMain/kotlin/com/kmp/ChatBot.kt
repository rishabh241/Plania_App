package com.kmp

class ChatBot {
    private val staticResponse = "Hello! This is a static response."

    fun sendMessage(userMessage: String): String {
        if(userMessage.contains("berlin")){
            return "Hitler"
        }else{
            return staticResponse
        }
        // Simulate sending the user's message and getting a response

    }
}