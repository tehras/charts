package com.github.tehras.charts.piechart.animation

import androidx.compose.animation.core.TweenSpec
import androidx.compose.runtime.Composable

@Composable
fun simpleChartAnimation() = TweenSpec<Float>(
    durationMillis = 500
)