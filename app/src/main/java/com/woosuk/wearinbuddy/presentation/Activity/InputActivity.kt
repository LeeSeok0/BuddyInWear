package com.woosuk.wearinbuddy.presentation.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.woosuk.wearinbuddy.presentation.ViewModel.SleepViewModel

class InputActivity : ComponentActivity() {

    private val sleepViewModel: SleepViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val savedValue = sharedPreferences.getString("user_input", null)

        if (savedValue.isNullOrEmpty()) {
            setContent {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        InputScreen(sleepViewModel)
                    }
                }
            }
        } else {
            // 이미 저장된 값이 있는 경우 SleepActivity로 이동
            val intent = Intent(this, SleepActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@Composable
fun InputScreen(sleepViewModel: SleepViewModel) {
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                with(sharedPreferences.edit()) {
                    putString("user_input", input)
                    apply()
                }
                val intent = Intent(context, SleepActivity::class.java)
                context.startActivity(intent)
                (context as ComponentActivity).finish()
            },
            modifier = Modifier
                .width(140.dp) // 버튼 너비 조정
                .background(Color.Transparent)
        ) {
            Text(text = "확인", color = Color.White)
        }
    }
}

