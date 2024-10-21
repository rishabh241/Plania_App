package com.kmp.Screens.Destination

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.kmp.BottomTab.FavTab
import com.kmp.BottomTab.FavTab.user
import com.kmp.CityList
import com.kmp.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class Destination : Screen {
    val user = Repository()
    var des_city: String = ""
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        var userData by remember { mutableStateOf<String?>("Loading...?") }

        println("Rishabh")

        LaunchedEffect(Unit){
            scope.launch {
                val data = FavTab.getUserData()
                userData = data?:"No Data"
            }
        }

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
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 65.dp)
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
                            text = "DESTINATION",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D2C54)
                        )
                        Text(
                            text = "What is your next destination?",
                            fontSize = 16.sp,
                            color = Color(0xFFE95A22)
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        val mockCitiess = CityList.getAllCountries()
                        des_city = DestinationSearchView(City = mockCitiess, data = userData)
                        Button(onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
//                                user.writeCity("database/City","$des_city")
                            }

                            navigator?.push(
                                TripDates()
                            )
                        }) {
                            Text("Next")
                        }
                    }

                }

            }
        }

    }
}

@Composable
fun DestinationSearchView(City: List<String>, data:String?):String{
    var user_city = "Paris"
    var searchQuery by remember { mutableStateOf("") }
    var filteredCities by remember { mutableStateOf(listOf<String>()) }


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        TextField(
            value = searchQuery,
            onValueChange = {query->
                searchQuery = query
//                filteredCities = City[query]?.take(5)?: listOf()
                filteredCities = if(query.isEmpty()){
                    listOf()
                }else{
                    City.filter { city ->
                        city.contains(query, ignoreCase = true)
                    }.take(5)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(50.dp)),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            placeholder = { Text("Search $data...") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        if(filteredCities.size ==0){
            filteredCities = listOf("Berlin, Germany",
                "Dresden, Germany",
                "Munich, Germany",
                "Hamburg, Germany").sorted()
        }
        LazyColumn {
            items(filteredCities) { city ->
                CitySuggestionItem(city=city, onCityClick = {selectedCity->
                    searchQuery = selectedCity
                    filteredCities = listOf(selectedCity)
                    println("Rishabh"+selectedCity)
                })
            }
        }
    }
    println(user_city)
    return user_city
}


@Composable
fun CitySuggestionItem(city:String,onCityClick:(String)->Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .clickable {
                onCityClick(city)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Place, // Place icon for city
            contentDescription = null,
            modifier = Modifier.padding(8.dp).alpha(0.7f),
            tint = Color.Red
        )
        Text(
            text = city,
            modifier = Modifier.padding(8.dp)
        )
    }
}



