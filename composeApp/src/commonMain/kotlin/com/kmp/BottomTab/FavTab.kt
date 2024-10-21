package com.kmp.BottomTab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.kmp.Repository
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Heart
import compose.icons.fontawesomeicons.solid.Home
import kotlinx.coroutines.launch

object FavTab: Tab {
    val user = Repository()
    suspend fun getUserData():String? {
        val userData = user.fetchUserData()
        return userData
    }

    override val options: TabOptions
        @Composable
        get(){
            val icon = rememberVectorPainter(FontAwesomeIcons.Solid.Heart)
            return remember {
                TabOptions(
                    index = 3u,
                    title = "Fav",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()

        var userData by remember { mutableStateOf<String?>("Loading...?") }

        LaunchedEffect(Unit){
            scope.launch {
                val data = getUserData()
                userData = data?:"No Data"
            }
        }
        Scaffold {
            Text(text = "Fav Tab Data:$userData")
        }
    }
}