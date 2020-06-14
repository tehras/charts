package com.github.tehras.charts.ui

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue

sealed class ChartScreen {
    object SelectChart : ChartScreen()
    object Pie : ChartScreen()
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