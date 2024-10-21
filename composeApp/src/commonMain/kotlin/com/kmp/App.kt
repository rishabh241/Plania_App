package com.kmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarData
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.kmp.BottomTab.ChatTab
import com.kmp.BottomTab.FavTab
import com.kmp.BottomTab.HomeTab
import com.kmp.BottomTab.ProfileTab


@Composable
fun App() {
    MaterialTheme {
        TabNavigator(HomeTab) {
            Scaffold(
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomeTab )
                        TabNavigationItem(ChatTab )
                        TabNavigationItem(FavTab )
                        TabNavigationItem(ProfileTab)
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        modifier = Modifier.fillMaxWidth().background(Color.Black),
        icon = {
            val iconSize = if (tabNavigator.current == tab) 30.dp else 20.dp
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title,
                tint = Color.White,
                modifier = Modifier.size(iconSize)
            )
        }
    )

}