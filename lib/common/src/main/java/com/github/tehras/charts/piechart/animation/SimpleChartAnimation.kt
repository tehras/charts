package com.github.tehras.charts.piechart.animation

import androidx.compose.animation.core.TweenSpec
import androidx.compose.runtime.Composable

@Composable
fun SimpleChartAnimation() = TweenSpec<Float>(
    durationMillis = 500
)