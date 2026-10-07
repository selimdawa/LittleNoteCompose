package com.flatcode.littlenote.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flatcode.littlenote.utils.DATA.MC_TRACK

@Composable
fun CustomProgressBar(
    modifier: Modifier = Modifier, size: Dp = 40.dp, strokeWidth: Dp = 4.dp, color: Color = MC_TRACK
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        ), label = "rotationAngle"
    )

    Canvas(
        modifier = modifier.size(size)
    ) {
        val sweepGradient = Brush.sweepGradient(
            colors = listOf(
                color.copy(alpha = 0f), color
            )
        )

        rotate(rotation) {
            drawArc(
                brush = sweepGradient,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                size = Size(size.toPx() - strokeWidth.toPx(), size.toPx() - strokeWidth.toPx()),
                topLeft = Offset(strokeWidth.toPx() / 2, strokeWidth.toPx() / 2)
            )
        }
    }
}