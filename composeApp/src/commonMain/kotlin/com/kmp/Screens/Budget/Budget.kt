package com.kmp.Screens.Budget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RadioButton
import androidx.compose.material.RangeSlider
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.kmp.Screens.Interests.Interests
import kotlin.math.roundToInt

class Budget : Screen {
    @OptIn(ExperimentalMaterialApi::class)
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
                            for (step in 2..4) {
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
                            for (step in 5..5) {
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
                            text = "BUDGET",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D2C54)
                        )
                        Text(
                            text = "What is your budget?",
                            fontSize = 16.sp,
                            color = Color(0xFFE95A22)
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        var selectedOption by remember { mutableStateOf("Daily") }
                        var sliderPosition2 by remember { mutableStateOf(100f..400f) }
                        val sliderRange2 = if (selectedOption == "Daily") 0f..500f else 0f..3000f

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            RadioButtonWithLabel(
                                text = "Daily",
                                selected = selectedOption == "Daily",
                                onClick = { selectedOption = "Daily"
                                sliderPosition2 = 50f..400f}
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            RadioButtonWithLabel(
                                text = "Overall",
                                selected = selectedOption == "Overall",
                                onClick = { selectedOption = "Overall"
                                    sliderPosition2 = 1000f..2000f }
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Min: €${sliderPosition2.start.roundToInt()}")
                                Text(text = "Max: €${sliderPosition2.endInclusive.roundToInt()}")
                            }
                            val steps = ((sliderRange2.endInclusive - sliderRange2.start) / 10).toInt()
                            Spacer(modifier = Modifier.height(10.dp))
                            RangeSlider(
                                value = sliderPosition2,
                                onValueChange = {range -> sliderPosition2 = range },
                                valueRange = sliderRange2,
                                steps = steps,
                                onValueChangeFinished = {}
                            )
                        }

                        Row {
                            Button(onClick = {
                                navigator?.pop()
                            }) {
                                Text("Prev")
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(onClick = {
                                navigator?.push(Interests())
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
fun RadioButtonWithLabel(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null // null to use Row's clickable
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = Color.Black, fontSize = 16.sp)
    }

}