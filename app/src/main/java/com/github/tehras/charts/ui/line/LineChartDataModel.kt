package com.github.tehras.charts.ui.line

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.LineChartData.Point
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.point.HollowCircularPointDrawer
import com.github.tehras.charts.line.renderer.point.NoPointDrawer
import com.github.tehras.charts.line.renderer.point.PointDrawer
import com.github.tehras.charts.ui.line.LineChartDataModel.PointDrawerType.*

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
    var horizontalOffset by mutableStateOf(5f)
    var pointDrawerType by mutableStateOf(Filled)
    val pointDrawer: PointDrawer
        get() {
            return when (pointDrawerType) {
                None -> NoPointDrawer
                Filled -> FilledCircularPointDrawer()
                Hollow -> HollowCircularPointDrawer()
            }
        }

    private fun randomYValue(): Float = (100f * Math.random()).toFloat() + 45f

    enum class PointDrawerType { None, Filled, Hollow }
}