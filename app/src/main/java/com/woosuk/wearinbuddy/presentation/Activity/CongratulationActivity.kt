package com.woosuk.wearinbuddy.presentation.Activity


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.wear.compose.material.Text
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.woosuk.wearinbuddy.R

class CongratulationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 3초 후에 Activity 종료
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 3000) // 3000 밀리초 = 3초

        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AndroidView(
                        factory = {
                            LottieAnimationView(it).apply {
                                setAnimation(R.raw.fireworks) // firework.json 파일을 추가해야 합니다.
                                repeatCount = LottieDrawable.INFINITE
                                playAnimation()
                            }
                        },
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
        }
    }
}
