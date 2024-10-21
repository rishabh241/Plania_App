package com.kmp.Screens.Interests

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.kmp.Screens.Budget.Budget
import com.kmp.Screens.TripEvents.Events

@OptIn(ExperimentalLayoutApi::class)
class Interests : Screen {
    @Composable
    override fun Content() {
        Scaffold {
            val navigator = LocalNavigator.current
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
                            for (step in 2..5) {
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
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "INTERESTS",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D2C54)
                        )
//                        Text(
//                            text = "How many travellers will visit the city?",
//                            fontSize = 16.sp,
//                            color = Color(0xFFE95A22)
//                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        val selectedTags = remember { mutableStateMapOf<String, Boolean>() }

                        val tags = listOf(
                            "Architecture", "Exhibitions", "Arts & museums", "Cruises",
                            "Culture & History", "Bus tours", "Nature", "Hop-on Hop-off",
                            "Music", "Kids", "Bike", "Sightseeing", "Entry tickets"
                        )

                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            tags.forEach {tag->
                                InterestTag(
                                    text = tag,
                                    isSelected = selectedTags[tag] == true,
                                    onSelectedChange = { isSelected ->
                                        selectedTags[tag] = isSelected
                                    })
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))

                        Row {
                            Button(onClick = {
                                navigator?.pop()
                            }) {
                                Text("Prev")
                            }
                            Spacer(modifier = Modifier.width(40.dp))
                            Button(onClick = {
                                navigator?.push(Events())
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
fun InterestTag(
    text: String,
    isSelected: Boolean,
    onSelectedChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(
                color = if (isSelected) Color(0xFFE95A22) else Color.White,
                shape = RoundedCornerShape(40.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFE95A22),
                shape = RoundedCornerShape(40.dp)
            )
            .clickable { onSelectedChange(!isSelected) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color(0xFFE95A22),
            fontSize = 12.sp
        )
    }
}