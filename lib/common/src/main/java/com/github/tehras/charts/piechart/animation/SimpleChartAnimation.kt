package com.github.tehras.charts.piechart.animation

import androidx.animation.TweenBuilder
import androidx.compose.Composable

@Composable
val SimpleChartAnimation = TweenBuilder<Float>().apply {
    duration = 500
}