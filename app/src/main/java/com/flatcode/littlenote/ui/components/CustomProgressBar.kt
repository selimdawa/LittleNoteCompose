package com.flatcode.littlenote.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flatcode.littlenote.utils.DATA.MC_TRACK

@Composable
fun CustomProgressBar(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    strokeWidth: Dp = 4.dp,
    color: Color = MC_TRACK
) {
    val angle = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        angle.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing)
            )
        )
    }

    val colors = listOf(
        color.copy(alpha = 0f), color
    )

    Canvas(modifier = modifier.size(size)) {
        val sweepGradient = Brush.sweepGradient(
            colors = colors
        )

        drawArc(
            brush = sweepGradient,
            startAngle = angle.value,
            sweepAngle = 270f,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx())
        )
    }
}