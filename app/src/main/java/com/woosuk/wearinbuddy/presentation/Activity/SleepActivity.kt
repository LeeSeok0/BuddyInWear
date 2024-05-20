package com.woosuk.wearinbuddy.presentation.Activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.woosuk.wearinbuddy.presentation.ViewModel.SleepViewModel

class SleepActivity : ComponentActivity() {

    private val sleepViewModel: SleepViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("user_input", null)

        username?.let {
            sleepViewModel.fetchSleepData(it)
        }

        setContent {
            Scaffold {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    SleepScreen(sleepViewModel)
                }
            }
        }
    }
}

@Composable
fun SleepScreen(viewModel: SleepViewModel) {
    val sleepData by viewModel.sleepData

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgress(
            sleepStartTime = sleepData.sleepStartTime,
            sleepEndTime = sleepData.sleepEndTime,
            modifier = Modifier.size(200.dp),
            strokeWidth = 11f,
            color = Color.White
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "수면시간",
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${sleepData.sleepDuration} 시간",
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "수면 시작 시간: ${sleepData.sleepStartTime}",
            fontSize = 10.sp,
            color = Color.Gray,
        )
        Text(
            text = "수면 종료 시간: ${sleepData.sleepEndTime}",
            fontSize = 10.sp,
            color = Color.Gray,
        )
    }
}

@Composable
fun CircularProgress(
    sleepStartTime: java.time.LocalTime,
    sleepEndTime: java.time.LocalTime,
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.White,
    strokeWidth: Float = 8f
) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        val radius = (size.minDimension - strokeWidth) / 2
        val centerX = size.width / 2
        val centerY = size.height / 2

        val formatter = java.time.format.DateTimeFormatter.ofPattern("HH:mm")
        val startTime = java.time.LocalTime.parse(sleepStartTime.toString(), formatter)
        val endTime = java.time.LocalTime.parse(sleepEndTime.toString(), formatter)

        val startAngle = (startTime.toSecondOfDay() / 86400f) * 360 - 90
        val endAngle = (endTime.toSecondOfDay() / 86400f) * 360 - 90
        val sweepAngle = if (endAngle >= startAngle) endAngle - startAngle else 360 - (startAngle - endAngle)

        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )
    }
}
