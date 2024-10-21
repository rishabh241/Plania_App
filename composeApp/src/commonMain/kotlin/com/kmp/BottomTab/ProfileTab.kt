package com.kmp.BottomTab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.kmp.Repository
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

object ProfileTab: Tab {
    val repo = Repository()
    @Composable
    override fun Content() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Profile Tab")

            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    repo.writeData("database/rishabh2",5)
                }
            }){

                Text("sendData")
            }

        }
    }
    override val options: TabOptions
        @Composable
        get(){
            val icon = rememberVectorPainter(FontAwesomeIcons.Solid.User)
            return remember {
                TabOptions(
                    index = 2u,
                    title = "Profile",
                    icon = icon
                )
            }
        }
}

