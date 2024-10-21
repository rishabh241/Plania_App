package com.kmp.BottomTab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarData
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.kmp.Screens.Destination.Destination
import com.kmp.Screens.Destination.TripDates
import com.kmp.Screens.TripEvents.Events
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Home
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import voyagerproject.composeapp.generated.resources.Res
import voyagerproject.composeapp.generated.resources.compose_multiplatform

object HomeTab : Tab {

    @Composable
    override fun Content() {
        Scaffold(
            topBar = { MyTopBar() }
        ) {

            Navigator(Destination()) { navigator ->
                SlideTransition(navigator)
            }

//            if (das) {
//                Navigator(Destination()) { navigator ->
//                    SlideTransition(navigator)
//                }
//            } else {
//                Navigator(TripDates()) { navigator ->
//                    SlideTransition(navigator)
//                }
//            }
            Spacer(modifier = Modifier.height(16.dp))
//            Text(text = "Data from Firebase: $data")

        }
    }


    override val options: TabOptions
        @Composable

        get() {
            val icon = rememberVectorPainter(FontAwesomeIcons.Solid.Home)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Home",
                    icon = icon,
                )
            }
        }


}

@Composable
fun MyTopBar() {
    TopAppBar(
        backgroundColor = Color.Black,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(start = 16.dp)
            )


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle user icon click */ }) {
                    Icon(
                        imageVector = Icons.Default.Person, // Replace with your user icon resource ID
                        contentDescription = "User",
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(onClick = { /* Handle menu icon click */ }) {
                    Icon(
                        imageVector = Icons.Default.Menu, // Replace with your menu icon resource ID
                        contentDescription = "Menu",
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)

                    )
                }
            }
        }
    }
}