package com.github.tehras.charts.piechart.animation

import androidx.animation.TweenBuilder
import androidx.compose.Composable
import androidx.ui.res.integerResource

@Composable
val SimpleChartAnimation = TweenBuilder<Float>().apply {
    duration = +integerResource(id = android.R.integer.config_mediumAnimTime)
}