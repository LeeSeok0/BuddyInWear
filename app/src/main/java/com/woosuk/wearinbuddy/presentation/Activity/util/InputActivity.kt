package com.woosuk.wearinbuddy.presentation.Activity.util

import SleepViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.woosuk.wearinbuddy.presentation.Activity.activity.WorkOutActivity
import com.woosuk.wearinbuddy.presentation.Activity.sleep.SleepActivity
import com.woosuk.wearinbuddy.presentation.ViewModel.ActivityViewModel

class InputActivity : ComponentActivity() {

    private val sleepViewModel: SleepViewModel by viewModels()
    private val activityViewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val savedValue = sharedPreferences.getString("hash_Code", null)
        val originActivity = intent.getStringExtra("origin_activity")

        if (!savedValue.isNullOrEmpty() && !originActivity.isNullOrEmpty()) {
            val intent = when (originActivity) {
                "sleep" -> Intent(this, SleepActivity::class.java)
                "workout" -> Intent(this, WorkOutActivity::class.java)
                else -> null
            }
            intent?.let {
                startActivity(it)
                finish()
            }
        } else {
            setContent {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        InputScreen(originActivity)
                    }
                }
            }
        }
    }
}

@Composable
fun InputScreen(originActivity: String?) {
    val context = LocalContext.current
    var input by remember { mutableStateOf("") }
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(13.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "6자리 값을 입력하세요",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 13.dp)
        )
        TextField(
            value = input,
            onValueChange = {
                if (it.length <= 6) input = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = androidx.compose.ui.text.TextStyle(
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 17.sp
            ),
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .padding(bottom = 0.dp), // 줄 간격 조정
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                textColor = Color.White,
                backgroundColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(8.dp)) // 텍스트 필드와 버튼 사이의 간격 조정
        Button(
            onClick = {
                val hashCode = input.toString()
                if (hashCode.isNotEmpty()) {
                    with(sharedPreferences.edit()) {
                        putString("hash_Code", hashCode)
                        apply()
                    }
                    val intent = when (originActivity) {
                        "sleep" -> Intent(context, SleepActivity::class.java)
                        "workout" -> Intent(context, WorkOutActivity::class.java)
                        else -> null
                    }
                    intent?.let {
                        context.startActivity(it)
                        (context as ComponentActivity).finish()
                    }
                } else {
                    Toast.makeText(context, "유효한 해시코드가 아닙니다.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .width(140.dp) // 버튼 너비 조정
                .background(Color.Transparent)
        ) {
            Text(text = "확인", color = Color.White)
        }
    }
}
