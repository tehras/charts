package com.github.tehras.charts.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class ChartScreen {
    object SelectChart : ChartScreen()
    object Pie : ChartScreen()
    object Bar : ChartScreen()
    object Line : ChartScreen()
}

object ChartScreenStatus {
    var currentChart by mutableStateOf<ChartScreen>(ChartScreen.SelectChart)
        private set

    fun navigateTo(screen: ChartScreen) {
        currentChart = screen
    }

    fun navigateHome() {
        navigateTo(ChartScreen.SelectChart)
    }
}