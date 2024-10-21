package com.kmp.Screens.Details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.kmp.Model.EventModel

class Details(val item: EventModel): Screen {
    @Composable
    override fun Content() {

        Text(
            text = "${item.title}"
        )
    }
}