package com.woosuk.wearinbuddy.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Scaffold

class SleepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{

            Scaffold {
                // progress는 0.0에서 1.0 사이의 값
                WaterLevelIndicator(progress = 0.5f)  // 50%의 물 높이
            }

        }
    }
}
@Composable
fun WaterLevelIndicator(progress: Float) {
    // 화면의 높이를 기준으로 물의 높이 계산
    val screenHeight = 300f  // 예시로 300px로 설정
    val waterHeight = remember { Animatable(0f) }

    // 애니메이션 효과 적용
    LaunchedEffect(key1 = progress) {
        waterHeight.animateTo(
            targetValue = screenHeight * progress,
            animationSpec = tween(durationMillis = 1000)  // 1초 동안 부드럽게 애니메이션
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(
                color = Color.Blue,
                topLeft = Offset(x = 0f, y = screenHeight - waterHeight.value),
                size = Size(width = size.width, height = waterHeight.value)
            )
        }
    }
}