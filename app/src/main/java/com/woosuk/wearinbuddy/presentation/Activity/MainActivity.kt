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
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.*
import com.woosuk.wearinbuddy.R
import com.woosuk.wearinbuddy.presentation.Activity.util.InputActivity

class MainActivity() : ComponentActivity(), Parcelable {
    constructor(parcel: Parcel) : this()

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
    val items = listOf("우울 정도 분석", "오늘의 운동량", "수면패턴")
    val icons = listOf(R.drawable.sad, R.drawable.running, R.drawable.moon)
    val state = rememberScalingLazyListState()
    val chipHeight = 48.dp
    val chipWidth = 180.dp
    val iconSize = 32.dp

    Scaffold(
        positionIndicator = {
            PositionIndicator(scalingLazyListState = state)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column {
                Button(
                    onClick = {
                        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                        with(sharedPreferences.edit()) {
                            remove("hash_Code")
                            apply()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "값 삭제", color = Color.White)
                }

                ScalingLazyColumn(
                    state = state,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    items(items.size) { index ->
                        Chip(
                            icon = {
                                Icon(
                                    painter = painterResource(id = icons[index % icons.size]),
                                    contentDescription = null,
                                    modifier = Modifier.size(iconSize),
                                    tint = Color.Unspecified
                                )
                            },
                            label = {
                                Text(
                                    text = items[index],
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                            },
                            colors = ChipDefaults.chipColors(
                                backgroundColor = Color.Gray
                            ),
                            onClick = {
                                when (items[index]) {
                                    "우울 정도 분석" -> {
                                        val intent = Intent(context, DepressedActivity::class.java)
                                        context.startActivity(intent)
                                    }
                                    "오늘의 운동량" -> {
                                        val intent = Intent(context, InputActivity::class.java).apply {
                                            putExtra("origin_activity", "workout")
                                        }
                                        context.startActivity(intent)
                                    }
                                    "수면패턴" -> {
                                        val intent = Intent(context, InputActivity::class.java).apply {
                                            putExtra("origin_activity", "sleep")
                                        }
                                        context.startActivity(intent)
                                    }
                                }
                            },
                            modifier = Modifier
                                .padding(0.5.dp)
                                .height(chipHeight)
                                .width(chipWidth)
                        )
                    }
                }
            }
        }
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
