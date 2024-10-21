package com.kmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.takeFrom
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashCompleted:()->Unit) {

    var isSplashVisible by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        delay(3000)  // 3-second delay
        isSplashVisible = false
        onSplashCompleted()  // Trigger action after splash screen
    }
    if (isSplashVisible) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                KamelImage(
                    asyncPainterResource("https://i.postimg.cc/kgwnsvm8/splash.png"),
                    "by",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
//                Spacer(modifier = Modifier.height(16.dp))
//                // Display app name or loading text
//                Text("Welcome to MyApp", fontSize = 24.sp)
            }
        }
    }
}