package com.woosuk.wearinbuddy.presentation.Activity.activity


import ActivityViewModel
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

class WorkOutActivity : ComponentActivity() {

    private val activityViewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("user_id", -1)

        if (userId != -1) {
            Log.d("WorkOutActivity", "Fetching activity data for user ID: $userId")
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
                    WorkOutScreen(activityViewModel)
                }
            }
        }
    }
}

@Composable
fun WorkOutScreen(viewModel: ActivityViewModel) {
    val activityData by viewModel.activityData.collectAsState()

    Log.d("WorkOutScreen", "Activity data: $activityData")

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgress(
                progress = activityData.steps / 10000f, // 예시로 목표 걸음수 10000 걸음으로 설정
                modifier = Modifier.size(150.dp),
                color = Color.Green,
                strokeWidth = 8f
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgress(
                progress = activityData.calories.toFloat() / 500f, // 예시로 목표 칼로리 500 칼로리로 설정
                modifier = Modifier.size(150.dp),
                color = Color.Red,
                strokeWidth = 8f
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "운동량 정보",
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "소모한 칼로리 : ${activityData.calories} kcal",
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 17.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "걸음수 : ${activityData.steps}",
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 17.sp,
            color = Color.White,
        )
    }
}
@Composable
fun CircularProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    strokeWidth: Float = 8f
) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        val radius = (size.minDimension - strokeWidth) / 2
        val centerX = size.width / 2
        val centerY = size.height / 2

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


