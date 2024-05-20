package com.woosuk.wearinbuddy.presentation.Activity.activity

import SleepViewModel
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.woosuk.wearinbuddy.presentation.ViewModel.ActivityViewModel
import java.time.LocalTime

class WorkOutActivity : ComponentActivity() {

    private val activityViewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("user_id", -1)

        if (userId != -1) {
            Log.d("WorkOutActivity", "Fetching sleep data for user ID: $userId")
            activityViewModel.fetchActivityData(userId)
        } else {
            Log.e("WorkOutActivity", "User ID not found in SharedPreferences")
        }

        setContent {
            Scaffold {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    SleepScreen(activityViewModel)
                }
            }
        }
    }
}

@Composable
fun SleepScreen(viewModel: ActivityViewModel) {
    val activityData by viewModel.activityData.collectAsState()

    Log.d("WorkOutScreen", "Sleep data: $activityData")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgress(
            steps = activityData.steps,
            modifier = Modifier.size(200.dp),
            strokeWidth = 11f,
            color = Color.White
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(13.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "운동량 정보",
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "걸음 수 : ${activityData.steps}",
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "칼로리 소모량 : ${activityData.calories} kcal",
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
        )
    }
}
@Composable
fun CircularProgress(
    steps: Int,
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.White,
    strokeWidth: Float = 8f,
    goal: Int = 10000 // 목표 걸음수
) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        val radius = (size.minDimension - strokeWidth) / 2
        val centerX = size.width / 2
        val centerY = size.height / 2

        // 목표 걸음수 대비 현재 걸음수의 비율을 계산하여 진행률을 설정합니다.
        val progress = (steps / goal.toFloat()).coerceIn(0f, 1f)
        val startAngle = -90f
        val sweepAngle = progress * 360

        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )
    }
}


