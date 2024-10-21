package com.kmp.Screens.Travellers

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.kmp.BottomTab.FavTab
import com.kmp.BottomTab.ProfileTab
import com.kmp.Repository
import com.kmp.Screens.Budget.Budget
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.PlusSquare
import compose.icons.fontawesomeicons.solid.MinusCircle
import compose.icons.fontawesomeicons.solid.PlusCircle
import compose.icons.fontawesomeicons.solid.PlusSquare
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class Travellers : Screen {
  val repo = Repository()
    @Composable
    override fun Content() {
        Scaffold {
            val navigator = LocalNavigator.current
            val scope = rememberCoroutineScope()
            var adultsCount by remember { mutableStateOf(0) }
            var kidsCount by remember { mutableStateOf(0) }
            var babiesCount by remember { mutableStateOf(0) }

            LaunchedEffect(Unit){
                scope.launch {
                    val kids = repo.fetchTraveller("database/traveller/kids")
                    val adult = repo.fetchTraveller("database/traveller/adult")
                    val babies = repo.fetchTraveller("database/traveller/babies")
                    kidsCount = kids?:0
                    adultsCount = adult?:0
                    babiesCount = babies?:0
                }
            }

            Box(
                modifier = Modifier.fillMaxSize().background(Color.Gray)
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .padding(horizontal = 15.dp, vertical = 20.dp)
                        .align(Alignment.Center)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(35.dp)
                                    .background(Color.Blue, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "1", // Text inside the Box
                                    color = Color.White, // Text color
                                    fontSize = 18.sp, // Text size
                                )
                            }
                            for (step in 2..3) {
                                Box(
                                    modifier = Modifier
                                        .size(height = 2.dp, width = 24.dp)
                                        .background(Color.Blue)
                                )
                                Box(
                                    modifier = Modifier
                                        .size(35.dp)
                                        .background(Color.Blue, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$step", // Text inside the Box
                                        color = Color.White, // Text color
                                        fontSize = 18.sp, // Text size
                                    )
                                }
                            }
                            for (step in 4..5) {
                                Box(
                                    modifier = Modifier
                                        .size(height = 2.dp, width = 24.dp)
                                        .background(Color.Gray)
                                )
                                Box(
                                    modifier = Modifier
                                        .size(35.dp)
                                        .background(Color.Gray, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$step", // Text inside the Box
                                        color = Color.White, // Text color
                                        fontSize = 18.sp, // Text size
                                    )
                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "TRAVELLERS",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D2C54)
                        )
                        Text(
                            text = "How many travellers will visit the city?",
                            fontSize = 16.sp,
                            color = Color(0xFFE95A22)
                        )



                        Spacer(modifier = Modifier.height(30.dp))

                        CounterRow(
                            title = "ADULTS",
                            description = "13 years and more",
                            count = adultsCount,
                            onCountChanged = {newCount-> adultsCount = newCount}
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .sizeIn(minHeight = 1.dp)
                                .background(Color.Gray)
                        )
                        CounterRow(
                            title = "KIDS",
                            description = "Between 2 and 12 years",
                            count = kidsCount,
                            onCountChanged = {newCount-> kidsCount = newCount}
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .sizeIn(minHeight = 1.dp)
                                .background(Color.Gray)
                        )
                        CounterRow(
                            title = "BEBIES",
                            description = "Less than 2 years",
                            count = babiesCount,
                            onCountChanged = {newCount-> babiesCount = newCount}
                        )


                        Row {
                            Button(onClick = {
                                navigator?.pop()
                            }) {
                                Text("Prev")
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(onClick = {
                                navigator?.push(Budget())
                                CoroutineScope(Dispatchers.IO).launch {
                                    repo.writeData("database/traveller/babies",
                                        babiesCount
                                    )
                                    repo.writeData("database/traveller/adult",
                                        adultsCount
                                    )
                                    repo.writeData("database/traveller/kids",
                                        kidsCount
                                    )
                                }
                            }) {


                                Text("Next")
                            }
                        }

                    }

                }

            }
        }
    }
}

@Composable
fun CounterRow(
    title: String,
    description: String,
    count: Int,
    onCountChanged: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = description, color = Color.Gray, fontSize = 14.sp)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { if (count > 0) onCountChanged(count - 1) }) {
                Icon(imageVector = FontAwesomeIcons.Solid.MinusCircle, contentDescription = null, modifier = Modifier.size(25.dp))
            }
            Text(
                text = count.toString(),
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            IconButton(onClick = {if (count <5)onCountChanged(count +1) }) {
                Icon(imageVector = FontAwesomeIcons.Solid.PlusCircle, contentDescription = null,modifier = Modifier.size(25.dp))
            }
        }
    }
}