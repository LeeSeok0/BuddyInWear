package com.woosuk.wearinbuddy.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class SleepActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{

            Scaffold {
                SleepPercent()
            }

        }
    }
@Composable
fun SleepPercent() {
    Text( text = "수면중 뒤척인 시간",
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 53.dp, top = 50.dp),)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 85.dp), // 상단 패딩을 추가하여 요소들을 아래로 이동
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "ㅇㅇㅇㅇㅇ",
            fontSize = 10.sp,
            color = Color.Black)
        }
    }
}