package com.woosuk.wearinbuddy.presentation.Activity.sleep

import SleepViewModel
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.woosuk.wearinbuddy.R

class SleepActivity : ComponentActivity() {

    private val sleepViewModel: SleepViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val hashCode = sharedPreferences.getString("hash_Code", "")

        if (hashCode != "") {
            Log.d("SleepActivity", "Fetching sleep data for user ID: $hashCode")
            if (hashCode != null) {
                sleepViewModel.fetchSleepData(hashCode)
            }
        } else {
            Log.e("SleepActivity", "User ID not found in SharedPreferences")
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
    val sleepData by viewModel.sleepData.collectAsState()

    Log.d("SleepScreen", "Sleep data: $sleepData")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.moon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                )
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
                    text = "수면 시작 시간: ${sleepData.sleepBedtimeStart}",
                    fontSize = 10.sp,
                    color = Color.Gray,
                )
                Text(
                    text = "수면 종료 시간: ${sleepData.sleepBedtimeEnd}",
                    fontSize = 10.sp,
                    color = Color.Gray,
                )
            }
        }
    }
}
//@Composable
//fun CircularProgress(
//    sleepStartTime: LocalTime,
//    sleepEndTime: LocalTime,
//    modifier: Modifier = Modifier,
//    color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.White,
//    strokeWidth: Float = 8f
//) {
//    androidx.compose.foundation.Canvas(modifier = modifier) {
//        val radius = (size.minDimension - strokeWidth) / 2
//        val centerX = size.width / 2
//        val centerY = size.height / 2
//
//        val startAngle = (sleepStartTime.toSecondOfDay() / 86400f) * 360 - 90
//        val endAngle = (sleepEndTime.toSecondOfDay() / 86400f) * 360 - 90
//        val sweepAngle = if (endAngle >= startAngle) endAngle - startAngle else 360 - (startAngle - endAngle)
//
//        drawArc(
//            color = color,
//            startAngle = startAngle,
//            sweepAngle = sweepAngle,
//            useCenter = false,
//            style = Stroke(strokeWidth, cap = StrokeCap.Round)
//        )
//    }
//}

