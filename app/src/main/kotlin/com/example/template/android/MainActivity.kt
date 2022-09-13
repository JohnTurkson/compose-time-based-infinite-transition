package com.example.template.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.template.android.ui.theme.TemplateTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemplateTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Animations()
                }
            }
        }
    }
}

@Composable
fun Animations() {
    val duration = 5000
    
    var clockValue by remember {
        mutableStateOf(LocalDateTime.now().toString())
    }
    var timerValue by remember {
        mutableStateOf(LocalDateTime.now().toString())
    }
    
    val infiniteTransition = rememberInfiniteTransition()
    val clock by infiniteTransition.animateFloatByTime(
        duration = duration,
        offset = 0,
    ) {
        clockValue = LocalDateTime.now().toString()
    }
    val timer by infiniteTransition.animateFloatByTime(
        duration = duration,
        offset = (System.currentTimeMillis() % duration).toInt(),
    ) {
        timerValue = LocalDateTime.now().toString()
    }
    
    Column {
        LinearProgressIndicator(clock, modifier = Modifier.fillMaxWidth())
        Text(clockValue)
        LinearProgressIndicator(timer, modifier = Modifier.fillMaxWidth())
        Text(timerValue)
    }
}
