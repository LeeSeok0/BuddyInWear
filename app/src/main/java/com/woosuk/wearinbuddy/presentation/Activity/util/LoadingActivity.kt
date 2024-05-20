package com.woosuk.wearinbuddy.presentation.Activity.util

import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.woosuk.wearinbuddy.R
import com.woosuk.wearinbuddy.presentation.Activity.MainActivity
import kotlin.random.Random  // 이 위치로 옮겨주세요.

class LoadingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LoadingIndicatorWithImage()
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()  // 종료
        }, 3000)  // 3초 대기
    }
}

@Composable
fun LoadingIndicatorWithImage() {
    // List of drawable resource IDs with corresponding texts
    val loadingData = listOf(
        Pair(R.drawable.love, "Waiting for you..."),
        Pair(R.drawable.seeyou1, "Ready when you are..."),
        Pair(R.drawable.two0, "Let's get started!")
    )
    // Randomly select an image and text
    val randomLoadingData = loadingData[Random.nextInt(loadingData.size)]

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            strokeWidth = 4.dp
        )
        Image(
            painter = painterResource(id = randomLoadingData.first),
            contentDescription = "Center Image",
            modifier = Modifier.matchParentSize()
        )
        Text(
            randomLoadingData.second,
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .background(Color.Black.copy(alpha = 0.5f))
        )
    }
}
