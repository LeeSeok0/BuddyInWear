package com.woosuk.wearinbuddy.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class DepressedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{

            RainEffect()
            DepressedCircle(depressionLevel = 5) // 우울 정도를 5로 설정
            StressCircle(depressionLevel = 3)  // 스트레스정도를 3로 설정
        }
    }
}

@Composable
fun DepressedCircle(depressionLevel: Int) {
    Text( text = "우울정도",
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 85.dp, top = 50.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding( top = 85.dp), // 상단 패딩을 추가하여 요소들을 아래로 이동
        horizontalArrangement = Arrangement.Center
    ) {

        for (i in 0 until depressionLevel) {
            Canvas(modifier = Modifier
                .size(25.dp) // 동그라미 크기를 50dp에서 25dp로 줄임
                .padding(5.dp)

            ) {
                drawRoundRect(
                    color = Color.Black,
                    cornerRadius = CornerRadius(x = 50f, y = 50f) // 각도 반으로 조정
                )
            }
        }
    }
}
@Composable
fun StressCircle(depressionLevel: Int) {

    Text( text = "스트레스",
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 85.dp, top = 120.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp), // 상단 패딩을 추가하여 요소들을 아래로 이동
        horizontalArrangement = Arrangement.Center

    ) {
        for (i in 0 until depressionLevel) {
            Canvas(modifier = Modifier
                .size(25.dp) // 동그라미 크기를 50dp에서 25dp로 줄임
                .padding(5.dp)

            ) {
                drawRoundRect(
                    color = Color.Red,
                    cornerRadius = CornerRadius(x = 50f, y = 50f) // 각도 반으로 조정
                )
            }
        }
    }
}
@Composable
fun RainEffect() {
    val raindrops = remember { List(100) { Raindrop() } }
    val modifier = Modifier.fillMaxSize()

    Box(modifier = modifier) {
        Canvas(modifier = modifier) {
            raindrops.forEach { drop ->
                drawLine(
                    color = Color.Blue,
                    start = drop.position,
                    end = drop.position.copy(y = drop.position.y + drop.length),
                    strokeWidth = 2.dp.toPx(),
                    alpha = drop.alpha
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        while (isActive) {
            delay(16L) // Approximately 60 FPS
            raindrops.forEach { it.update() }
        }
    }
}

data class Raindrop(
    var position: androidx.compose.ui.geometry.Offset = androidx.compose.ui.geometry.Offset(
        x = (Math.random() * 1000).toFloat(),
        y = (Math.random() * 1000).toFloat()
    ),
    var speed: Float = (Math.random() * 10 + 5).toFloat(), // Speed of raindrop
    var length: Float = (Math.random() * 30 + 10).toFloat(), // Length of raindrop
    var alpha: Float = (Math.random() * 0.5 + 0.5).toFloat() // Transparency
) {
    fun update() {
        position = position.copy(y = position.y + speed)
        if (position.y > 1000) { // Reset raindrop when it moves off screen
            position = position.copy(y = 0f, x = (Math.random() * 1000).toFloat())
        }
    }
}