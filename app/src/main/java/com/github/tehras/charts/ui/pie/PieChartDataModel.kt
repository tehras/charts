package com.github.tehras.charts.ui.pie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.github.tehras.charts.piechart.PieChartData

class PieChartDataModel {
    private var colors = mutableListOf(
        Color(0XFFF44336),
        Color(0XFFE91E63),
        Color(0XFF9C27B0),
        Color(0XFF673AB7),
        Color(0XFF3F51B5),
        Color(0XFF03A9F4),
        Color(0XFF009688),
        Color(0XFFCDDC39),
        Color(0XFFFFC107),
        Color(0XFFFF5722),
        Color(0XFF795548),
        Color(0XFF9E9E9E),
        Color(0XFF607D8B)
    )

    var sliceThickness by mutableStateOf(25f)
    var pieChartData by mutableStateOf(
        PieChartData(
            slices = listOf(
                PieChartData.Slice(
                    randomLength(),
                    randomColor()
                ),
                PieChartData.Slice(
                    randomLength(),
                    randomColor()
                ),
                PieChartData.Slice(
                    randomLength(),
                    randomColor()
                )
            )
        )
    )

    val slices
        get() = pieChartData.slices

    fun addSlice() {
        pieChartData = pieChartData.copy(
            slices = slices.toMutableList().apply {
                add(PieChartData.Slice(randomLength(), randomColor()))
            }.toList()
        )
    }

    fun removeSlice() {
        pieChartData = pieChartData.copy(
            slices = slices.toMutableList().apply {
                val lastSlice = slices[slices.size - 1]
                colors.add(lastSlice.color)

                remove(lastSlice)
            }.toList()
        )
    }

    private fun randomColor(): Color {
        val randomIndex = (Math.random() * colors.size).toInt()
        val color = colors[randomIndex]
        colors.removeAt(randomIndex)

        return color
    }

    private fun randomLength(): Float = (20 * Math.random() + 10).toFloat()
}