package com.kmp.Screens.TripEvents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.sharp.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.BottomTab.FavTab
import com.kmp.BottomTab.FavTab.user
import com.kmp.Model.EventModel
import com.kmp.Model.TranspostModel
import com.kmp.Repository
import com.kmp.Screens.Destination.Destination
import com.kmp.Screens.Details.Details
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.AllIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.brands.Github
import compose.icons.fontawesomeicons.solid.Bicycle
import compose.icons.fontawesomeicons.solid.Car
import compose.icons.fontawesomeicons.solid.Motorcycle
import compose.icons.fontawesomeicons.solid.Train
import compose.icons.fontawesomeicons.solid.Walking
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch


import voyagerproject.composeapp.generated.resources.Res
import voyagerproject.composeapp.generated.resources.compose_multiplatform

class Events : Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        var userData by remember { mutableStateOf<String?>("Loading...?") }


        LaunchedEffect(Unit) {
            scope.launch {
                val data = getCityData()
                userData = data ?: "No Data"
            }
        }
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(bottom = 40.dp)
            ) {
                Header(data = userData)
                EventList(StaticData())
            }
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.White)
//                    .padding(bottom = 40.dp)
//            ) {
//
//
//            }
        }
    }
}

@Composable
fun Header(data: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth().background(Color.Black)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "$data",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
//                Text(
//                    text = "The required budget is €806\n5 activities are planned in this trip",
//                    fontSize = 14.sp,
//                    color = Color(91, 153, 194)
//                )
            }
//            Column(
//                horizontalAlignment = Alignment.End,
//                verticalArrangement = Arrangement.Top
//            ) {
//                ActionButton("Map", icon = {
//                    Icon(
//                        Icons.Default.LocationOn,
//                        contentDescription = "Map",
//                        tint = Color(0xFF1C3A63)
//                    )
//                })
//                Spacer(modifier = Modifier.height(4.dp))
//                ActionButton("Refresh", icon = {
//                    Icon(
//                        Icons.Default.Refresh,
//                        contentDescription = "Map",
//                        tint = Color(0xFF1C3A63)
//                    )
//                })
//                Spacer(modifier = Modifier.height(4.dp))
//                ActionButton("Download", icon = {
//                    Icon(
//                        Icons.Default.Done,
//                        contentDescription = "Map",
//                        tint = Color(0xFF1C3A63)
//                    )
//                })
//                Spacer(modifier = Modifier.height(4.dp))
////                ActionButton("Refresh")
////                ActionButton("Download")
//            }
        }
//
    }
}

@Composable
fun ActionButton(text: String, icon: @Composable () -> Unit) {
    OutlinedButton(
        onClick = { /* Action */ },
        border = BorderStroke(1.dp, Color(0xFF1C3A63)),
        shape = RoundedCornerShape(50),

        modifier = Modifier
            .height(35.dp)
            .width(120.dp)
    ) {
        icon()

        Text(
            text = text, color = Color(0xFF1C3A63),
            fontSize = 10.sp
        )
    }
}

@Composable
fun EventList(eventItems: List<EventModel>) {
    Card(
        modifier = Modifier
            .fillMaxSize().padding(2.dp,2.dp,2.dp,15.dp),
        shape = RoundedCornerShape(20.dp,20.dp,0.dp,0.dp),
    ){
        AnimatedVisibility(eventItems.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.fillMaxSize().background(Color.White),
            ) {
                items(eventItems) { item ->
                    EventListItem2(item)
                }
            }
        }
    }


}

@Composable
fun EventListItem2(item: EventModel) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 0.dp,0.dp,0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = item.startTime,
                    modifier = Modifier.align(Alignment.CenterHorizontally), // Center vertically
                    fontSize = 12.sp,
                    color = Color.Black
                )
                Text(
                    text = item.endTime,
                    modifier = Modifier.align(Alignment.End), // Center vertically
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(3.dp))

            Box(
                modifier = Modifier
                    .height(30.dp)// Adjust this height to match the content
                    .width(1.dp)    // Thickness of the vertical line
                    .background(Color.Gray) // Line color
            )

            Spacer(modifier = Modifier.width(3.dp))


            EventListItem(item)
        }

        Divider(
            color = Color.Gray,   // You can change the color of the line here
            thickness = 1.dp,     // You can adjust the thickness of the line here
            modifier = Modifier.fillMaxWidth() // This ensures the line takes up the full width
        )

        TranspostRow(item)
        Divider(
            color = Color.Gray,   // You can change the color of the line here
            thickness = 1.dp,     // You can adjust the thickness of the line here
            modifier = Modifier.fillMaxWidth() // This ensures the line takes up the full width
        )
    }
}

@Composable
fun TranspostRow(items: EventModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 2.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        for (item in items.TransportList) {
            TransportOption(
                icon = item.icon, // Use appropriate icon
                time = item.time,
                isSelected = item.isShortest // This option is highlighted
            )
        }

    }
}


@Composable
fun TransportOption(icon: ImageVector, time: String, isSelected: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) Color.Red else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = time,
            color = if (isSelected) Color.Red else Color.Gray,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}


@Composable
fun EventListItem(item: EventModel) {
    var showPopup by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = {
                showPopup = true
            }),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(20.dp)    // Thickness of the vertical line
                    .background(Color(13, 146, 244)) // Line color
            )
            KamelImage(
                asyncPainterResource(item.imageUrl.toString()),
                "by",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(200.dp).aspectRatio(0.6f)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.padding(8.dp).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.title!!,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    color = Color.Black
                )
                Text(
                    text = item.description!!,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    for (star in 1..item.rating!!.toInt()) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Full Star",
                            tint = Color(255, 215, 0),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    for (star in item.rating.toInt()..4) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "Full Star",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = item.rating.toString(),
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                }
                Spacer(modifier = Modifier.height(3.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "Location",
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp).alpha(0.7f)
                    )
                    Text(
                        text = item.location!!,
                        fontSize = 16.sp,
                        color = Color.Gray,
                        maxLines = 1
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "From ${item.price}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "/person",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.alpha(0.5f)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* Handle favorite click */ }) {
                        Icon(
                            imageVector = if (item.isFavorite!!) Icons.Filled.Favorite else Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = if (item.isFavorite) Color.Red else Color.Gray,
                        )
                    }
                }
            }
        }
    }
    if (showPopup) {
        EventPopupDialog(item = item) {
            showPopup = false // Close the popup when dismissed
        }
    }
}

@Composable
fun EventPopupDialog(item: EventModel, content: () -> Unit) {
    Dialog(onDismissRequest = content) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Show detailed information of the event item in the popup
                Text(text = item.title!!, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.description!!, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))

                // Show Event Image
                KamelImage(
                    asyncPainterResource(item.imageUrl.toString()),
                    "Event Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Close button
                Button(onClick = content) {
                    Text(text = "Close")
                }
            }
        }
    }
}


fun StaticData(): List<EventModel> {
    val tours = listOf(
        EventModel(
            title = "Berlin: A Walk Through History",
            description = "History · Tour",
            rating = 4.5,
            location = "Reichstag 10117",
            price = "30€",
            imageUrl = "https://images.unsplash.com/photo-1449452198679-05c7fd30f416?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = true,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "40 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "12 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "8 min"),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "5 min", isShortest = true),
            ),
            startTime = "09:45",
            endTime = "10:50"
        ),
        EventModel(
            title = "Munich: Bavarian Culture and Beer Tour",
            description = "Culture · Food & Drink",
            rating = 4.8,
            location = "Marienplatz 80331",
            price = "50€",
            imageUrl = "https://plus.unsplash.com/premium_photo-1688147583826-1c169e82c57f?q=80&w=1975&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = false,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "15 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "12 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "10 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "20 min")
            ),
            startTime = "11:00",
            endTime = "13:05"
        ),
        EventModel(
            title = "Hamburg: Port and Harbor Cruise",
            description = "Water Tour · Cruise",
            rating = 4.7,
            location = "Landungsbrücken 20457",
            price = "25€",
            imageUrl = "https://images.unsplash.com/photo-1543169108-32ac15a21e05?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = true,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "5 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Car, "12 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "8 min"),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "15 min")
            ),
            startTime = "11:00",
            endTime = "13:05"
        ),
        EventModel(
            title = "Frankfurt: Financial District Skyline Tour",
            description = "City · Walking Tour",
            rating = 4.2,
            location = "Main Tower 60311",
            price = "20€",
            imageUrl = "https://images.unsplash.com/photo-1422360902398-0a91ff2c1a1f?q=80&w=2030&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = false,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "20 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "25 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "10 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "30 min")
            ),
            startTime = "11:00",
            endTime = "13:05"
        ),
        EventModel(
            title = "Berlin: A Walk Through History",
            description = "History · Tour",
            rating = 4.5,
            location = "Reichstag 10117",
            price = "30€",
            imageUrl = "https://images.unsplash.com/photo-1449452198679-05c7fd30f416?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = true,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "12 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "10 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "14 min"),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "18 min")
            ),
            startTime = "09:45",
            endTime = "10:50"
        ),
        EventModel(
            title = "Munich: Bavarian Culture and Beer Tour",
            description = "Culture · Food & Drink",
            rating = 4.8,
            location = "Marienplatz 80331",
            price = "50€",
            imageUrl = "https://plus.unsplash.com/premium_photo-1688147583826-1c169e82c57f?q=80&w=1975&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = false,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "18 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "8 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "16 min"),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "20 min")
            ),
            startTime = "11:00",
            endTime = "13:05"
        ),
        EventModel(
            title = "Hamburg: Port and Harbor Cruise",
            description = "Water Tour · Cruise",
            rating = 4.7,
            location = "Landungsbrücken 20457",
            price = "25€",
            imageUrl = "https://images.unsplash.com/photo-1543169108-32ac15a21e05?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = true,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "10 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "10 min"),
            ),
            startTime = "11:00",
            endTime = "13:05"
        ),
        EventModel(
            title = "Frankfurt: Financial District Skyline Tour",
            description = "City · Walking Tour",
            rating = 4.2,
            location = "Main Tower 60311",
            price = "20€",
            imageUrl = "https://images.unsplash.com/photo-1422360902398-0a91ff2c1a1f?q=80&w=2030&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = false,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "10 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "10 min"),
            ),
            startTime = "11:00",
            endTime = "13:05"
        ),
        EventModel(
            title = "Berlin: A Walk Through History",
            description = "History · Tour",
            rating = 4.5,
            location = "Reichstag 10117",
            price = "30€",
            imageUrl = "https://images.unsplash.com/photo-1449452198679-05c7fd30f416?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = true,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "10 min", isShortest = true),
            ),
            startTime = "09:45",
            endTime = "10:50"
        ),
        EventModel(
            title = "Munich: Bavarian Culture and Beer Tour",
            description = "Culture · Food & Drink",
            rating = 4.8,
            location = "Marienplatz 80331",
            price = "50€",
            imageUrl = "https://plus.unsplash.com/premium_photo-1688147583826-1c169e82c57f?q=80&w=1975&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = false,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "10 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "10 min"),
            ),
            startTime = "11:00",
            endTime = "13:05"
        ),
        EventModel(
            title = "Hamburg: Port and Harbor Cruise",
            description = "Water Tour · Cruise",
            rating = 4.7,
            location = "Landungsbrücken 20457",
            price = "25€",
            imageUrl = "https://images.unsplash.com/photo-1543169108-32ac15a21e05?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = true,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "10 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "10 min"),
            ),
            startTime = "11:00",
            endTime = "13:05"
        ),
        EventModel(
            title = "Frankfurt: Financial District Skyline Tour",
            description = "City · Walking Tour",
            rating = 4.2,
            location = "Main Tower 60311",
            price = "20€",
            imageUrl = "https://images.unsplash.com/photo-1422360902398-0a91ff2c1a1f?q=80&w=2030&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
            isFavorite = false,
            TransportList = listOf(
                TranspostModel(FontAwesomeIcons.Solid.Train, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Car, "10 min"),
                TranspostModel(FontAwesomeIcons.Solid.Motorcycle, "10 min", isShortest = true),
                TranspostModel(FontAwesomeIcons.Solid.Walking, "10 min"),
            ),
            startTime = "11:00",
            endTime = "13:05"
        ),
//        EventModel(
//            title = "Cologne: Cathedral and Chocolate Factory Tour",
//            description = "Landmarks · Food & Drink",
//            rating = 4.6,
//            location = "Cologne Cathedral 50667",
//            price = "40€",
//            imageUrl = "https://images.unsplash.com/photo-1471973772471-ecd9cf9eb04e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
//            isFavorite = true
//        ),
//        EventModel(
//            title = "Berlin: A Walk Through History",
//            description = "History · Tour",
//            rating = 4.5,
//            location = "Reichstag 10117",
//            price = "30€",
//            imageUrl = "https://images.unsplash.com/photo-1449452198679-05c7fd30f416?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
//            isFavorite = true
//        ),
//        EventModel(
//            title = "Munich: Bavarian Culture and Beer Tour",
//            description = "Culture · Food & Drink",
//            rating = 4.8,
//            location = "Marienplatz 80331",
//            price = "50€",
//            imageUrl = "https://plus.unsplash.com/premium_photo-1688147583826-1c169e82c57f?q=80&w=1975&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
//            isFavorite = false
//        ),
//        EventModel(
//            title = "Hamburg: Port and Harbor Cruise",
//            description = "Water Tour · Cruise",
//            rating = 4.7,
//            location = "Landungsbrücken 20457",
//            price = "25€",
//            imageUrl = "https://images.unsplash.com/photo-1543169108-32ac15a21e05?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
//            isFavorite = true
//        ),
//        EventModel(
//            title = "Frankfurt: Financial District Skyline Tour",
//            description = "City · Walking Tour",
//            rating = 4.2,
//            location = "Main Tower 60311",
//            price = "20€",
//            imageUrl = "https://images.unsplash.com/photo-1422360902398-0a91ff2c1a1f?q=80&w=2030&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
//            isFavorite = false
//        ),
//        EventModel(
//            title = "Cologne: Cathedral and Chocolate Factory Tour",
//            description = "Landmarks · Food & Drink",
//            rating = 4.6,
//            location = "Cologne Cathedral 50667",
//            price = "40€",
//            imageUrl = "https://images.unsplash.com/photo-1471973772471-ecd9cf9eb04e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",  // Replace with a real image URL
//            isFavorite = true
//        ),
    )

    return tours
}

suspend fun getCityData(): String? {
    val userData = user.fetchCityData()
    return userData
}