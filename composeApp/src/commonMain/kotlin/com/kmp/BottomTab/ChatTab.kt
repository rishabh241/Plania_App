package com.kmp.BottomTab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.kmp.ChatBot
import com.kmp.FirebaseDatabaseServices
import com.kmp.Repository
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Comment
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.Memory
import compose.icons.fontawesomeicons.solid.UserNinja
import io.ktor.http.HttpMessage
import kotlinx.coroutines.launch

object ChatTab : Tab {
    private val chatBot = ChatBot()

    @Composable
    override fun Content() {
        Scaffold(
            topBar = { ChatTopBar() }
        ) {
            ChatScreens(chatBot)
        }
    }

    override val options: TabOptions
        @Composable
        get(){
            val icon = rememberVectorPainter(FontAwesomeIcons.Solid.Comment)
            return remember {
                TabOptions(
                    index = 1u,
                    title = "Chat",
                    icon = icon
                )
            }
        }
}

@Composable
fun ChatTopBar() {
    TopAppBar(
        title = {
            Row {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = null
                    )
                }
                Text(
                    text = "Plania Chat Bot",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

            }

        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        elevation = 4.dp,
        backgroundColor = Color.White
    )
}

@Composable
fun ChatScreens(chatBot: ChatBot) {
    val scrollState = rememberScrollState()
    var userInput by remember { mutableStateOf(TextFieldValue()) }
    val conversation =
        remember { mutableStateListOf<Pair<Boolean, String>>() } // Pair (isUser, message)
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 60.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // Take up available vertical space
                    .fillMaxWidth(),
                reverseLayout = false
            ) {
                items(conversation) { message ->
                    if (message.first) {
                        UserMessageBubble(message.second)
                    } else {
                        BotMessageBubble(message.second)
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
                .background(Color(0xFFF2F2F2), RoundedCornerShape(24.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
                    .background(Color.Transparent)
                    .padding(12.dp),
                maxLines = 1,
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                decorationBox = {innerTextField ->
                    if (userInput.text.isEmpty()) {
                        Text(text = "Ask your query...",
                            color = Color.Gray
                            )
                    }
                    // <-- Add this
                    innerTextField()
                }
            )
            Button(
                onClick = {
                    // Add user message
                    if (userInput.text != "") {
                        conversation.add(Pair(true, userInput.text))

                        // Get bot response
                        coroutineScope.launch {
                            val botResponse = chatBot.sendMessage(userInput.text.toLowerCase())
                            conversation.add(Pair(false, botResponse))
                            userInput = TextFieldValue() // Clear input
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF007AFF)
                ),
                shape = RoundedCornerShape(24.dp),

            ) {
                Text(
                    "Send",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun UserMessageBubble(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .background(
                color = Color(0xFFFFE0B2),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = message,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun BotMessageBubble(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start)
            .background(
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = message,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
    }
}