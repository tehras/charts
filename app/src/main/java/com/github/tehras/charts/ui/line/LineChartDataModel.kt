package com.github.tehras.charts.ui.line

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.LineChartData.Point

class LineChartDataModel {
    var lineChartData by mutableStateOf(
        LineChartData(
            points = listOf(
                Point(randomYValue(), "Label1"),
                Point(randomYValue(), "Label2"),
                Point(randomYValue(), "Label3"),
                Point(randomYValue(), "Label4"),
                Point(randomYValue(), "Label5"),
                Point(randomYValue(), "Label6"),
                Point(randomYValue(), "Label7")
            )
        )
    )

    private fun randomYValue(): Float = (100f * Math.random()).toFloat() + 45f
}