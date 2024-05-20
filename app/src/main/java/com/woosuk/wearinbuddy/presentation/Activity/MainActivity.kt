@file:Suppress("DEPRECATION")

package com.woosuk.wearinbuddy.presentation.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.woosuk.wearinbuddy.R

class MainActivity() : ComponentActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MainScreen()
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {}

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val items = listOf("우울 정도 분석", "오늘의 운동량", "수면패턴", "우울글귀")
    val images = listOf(R.drawable.wade, R.drawable.drr, R.drawable.sleepimg, R.drawable.ember)

    ScalingLazyColumn(
        state = rememberScalingLazyListState(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        items(items.size) { index ->
            Chip(
                label = {
                    Text(
                        text = items[index],
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 18.sp)
                    )
                },
                colors = ChipDefaults.imageBackgroundChipColors(
                    backgroundImagePainter = painterResource(id = images[index % images.size])
                ),
                onClick = {
                    // '우울 정도 분석' 선택 시 DepressedActivity로 이동
                    if (items[index] == "우울 정도 분석") {
                        val intent = Intent(context, DepressedActivity::class.java)
                        context.startActivity(intent)
                    } else if (items[index] == "수면패턴") {
                        // 수면패턴 선택 시 InputActivity 또는 SleepActivity로 이동
                        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                        val savedValue = sharedPreferences.getString("user_input", null)

                        if (savedValue.isNullOrEmpty()) {
                            // 저장된 값이 없으면 InputActivity로 이동
                            val intent = Intent(context, InputActivity::class.java)
                            context.startActivity(intent)
                        } else {
                            // 저장된 값이 있으면 SleepActivity로 이동
                            val intent = Intent(context, SleepActivity::class.java)
                            context.startActivity(intent)
                        }
                    }
                },
                modifier = Modifier
                    .padding(0.5.dp)
                    .height(56.dp)
            )
        }
    }
    Button(
        onClick = {
            val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                remove("user_input")
                apply()
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "값 삭제", color = Color.White)
    }
}

@Preview(
    device = Devices.WEAR_OS_LARGE_ROUND,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}