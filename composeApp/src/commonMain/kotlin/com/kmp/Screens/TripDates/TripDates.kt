package com.kmp.Screens.Destination

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.YearMonth
import com.kizitonwose.calendar.core.atEndOfMonth
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.now
import com.kizitonwose.calendar.core.plusMonths
import com.kmp.Model.ContinuousSelectionHelper.getSelection
import com.kmp.Model.DateSelection
import com.kmp.Model.Student
import com.kmp.Screens.Travellers.Travellers
import com.kmp.Screens.TripDates.backgroundHighlight
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoDayNumber

@OptIn(ExperimentalFoundationApi::class)
class TripDates() : Screen {
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
                        .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 50.dp)
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
                            for (step in 2..2) {
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
                            for (step in 3..5) {
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
                            text = "TRIP DATES",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D2C54)
                        )
                        Text(
                            text = "How many days will you visit the city?",
                            fontSize = 16.sp,
                            color = Color(0xFFE95A22)
                        )
//                        Spacer(modifier = Modifier.height(30.dp))
                        Calender()
//                        Spacer(modifier = Modifier.height(30.dp))
                        Row {
                            Button(onClick = {
                                navigator?.pop()
                            }) {
                                Text("Prev")
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(onClick = {
                                navigator?.push(Travellers())
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
fun Calender(
    close: () -> Unit = {},
    dateSelected: (startDate: LocalDate, endDate: LocalDate) -> Unit = { _, _ -> }
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val today = remember { LocalDate.now() }
    var selection by remember { mutableStateOf(DateSelection()) }
    val daysOfWeek = remember { daysOfWeek() }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        ) {
            Column {
                val state = com.kizitonwose.calendar.compose.rememberCalendarState(
                    startMonth = startMonth,
                    endMonth = endMonth,
                    firstVisibleMonth = currentMonth,
                    firstDayOfWeek = daysOfWeek.first()
                )
                CalenderTop(
                    daysOfWeek = daysOfWeek,
                    selection = selection,
                )
                HorizontalCalendar(
                    state = state,
                    contentPadding = PaddingValues(bottom = 10.dp),
                    dayContent = { value ->
                        Day(
                            value,
                            today = today,
                            selection = selection,
                        ) { day ->
                            if (day.position == DayPosition.MonthDate && day.date >= today) {
                                selection = getSelection(
                                    clickedDate = day.date,
                                    dateSelection = selection,
                                )
                            }
                        }
                    },
                    monthHeader = {month-> MonthHeader(month) }

                )
            }


        }
    }
}

@Composable
private fun CalenderTop(
    modifier: Modifier = Modifier,
    daysOfWeek: List<DayOfWeek>,
    selection: DateSelection
) {
    Column(modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
//            val daysBetween = selection.daysBetween
//            val text = if (daysBetween == null) {
//                "Select dates"
//            } else {
//                // Ideally you'd do this using the strings.xml file
//                "$daysBetween ${if (daysBetween == 1) "day" else "days"} in Germany"
//            }
//            Text(
//                modifier = Modifier.padding(horizontal = 14.dp),
//                text = text,
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp,
//            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            ) {
                for (dayOfWeek in daysOfWeek) {
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                        text = dayOfWeek.name.get(0).toString() + dayOfWeek.name.get(1)
                            .toString() + dayOfWeek.name.get(2).toString(),
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}

@Composable
private fun Day(
    day: CalendarDay,
    today: LocalDate,
    selection: DateSelection,
    onClick: (CalendarDay) -> Unit,
) {
    var textColor = Color.Transparent

    val continuousSelectionColor = Color.Red.copy(alpha = 0.2f)
    Box(
        modifier = Modifier
            .aspectRatio(1f) // This is important for square-sizing!
            .clickable(
                enabled = day.position == DayPosition.MonthDate && day.date >= today,
                onClick = { onClick(day) },
            )
            .backgroundHighlight(
                day = day,
                today = today,
                selection = selection,
                selectionColor = Color.Red,
                continuousSelectionColor = continuousSelectionColor
            ) {
                textColor = it
            },
        contentAlignment = Alignment.Center,

        ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}
@Composable
private fun MonthHeader(calendarMonth: CalendarMonth){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "${calendarMonth.yearMonth.month.name} ${calendarMonth.yearMonth.year}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}



