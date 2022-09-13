package com.example.template.android

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun InfiniteTransition.animateFloatByTime(
    duration: Int,
    offset: Int = 0,
    initialValue: Float = 0f,
    targetValue: Float = 1f,
    onReset: () -> Unit = {},
): State<Float> {
    val animation = remember {
        infiniteRepeatable(
            animation = keyframes {
                durationMillis = duration
                initialValue at 0 with LinearEasing
                targetValue at duration with LinearEasing
            },
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(
                offsetMillis = offset,
                offsetType = StartOffsetType.FastForward
            )
        )
    }
    
    val state = this.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = animation,
    )
    
    var last by remember {
        mutableStateOf(initialValue)
    }
    
    LaunchedEffect(state.value) {
        if (state.value < last) onReset()
        last = state.value
    }
    
    return state
}
