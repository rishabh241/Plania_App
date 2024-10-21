package com.kmp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.kmp.Screens.TripEvents.Events
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var repo: DatabaseRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repo = DatabaseRepo(FirebaseImplAndroid())

        val dataState = mutableStateOf("")

        lifecycleScope.launch {
            val data = repo.getUsers("database/rishabh")
            Log.e("rishabh","$data")
            dataState.value = data?:"No Data Found"
        }


        Log.e("rishabh","Hyyy")
        setContent {
            MyApp()
        }
    }
}


@Composable
fun MyApp(){
    var showSplash by remember { mutableStateOf(true) }

    val transitionDuration = 1000
    
    Crossfade(targetState = showSplash, animationSpec =  tween(durationMillis = transitionDuration)) {isSplash->
        if (isSplash) {
            SplashScreen(onSplashCompleted = {
                showSplash = false
            })
        } else {

//        Navigator(App()) { navigator ->
//            SlideTransition(navigator)
//        }
            App()  // Replace with your main app screen
        }


    }


}
//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}