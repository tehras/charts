package com.github.tehras.charts.ui.bar

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import androidx.ui.graphics.Color
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.BarChartData.Bar
import com.github.tehras.charts.bar.BarChartData.LabelFormat.DrawLocation
import com.github.tehras.charts.bar.BarChartData.LabelFormat.DrawLocation.*

class BarChartDataModel {
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
    var barChartData by mutableStateOf(
        BarChartData(
            bars = listOf(
                Bar(
                    label = "Bar1",
                    value = randomValue(),
                    color = randomColor()
                ),
                Bar(
                    label = "Bar2",
                    value = randomValue(),
                    color = randomColor()
                ),
                Bar(
                    label = "Bar3",
                    value = randomValue(),
                    color = randomColor()
                )
            )
        )
    )
    val bars: List<Bar>
        get() = barChartData.bars
    var valueLocation: DrawLocation
        get() = barChartData.valueLabelFormat.drawLocation
        set(value) {
            val color = when (value) {
                Inside -> Color.White
                Outside, XAxis -> Color.Black
            }

            barChartData = barChartData.copy(
                valueLabelFormat = barChartData.valueLabelFormat.copy(
                    textColor = color,
                    drawLocation = value
                )
            )
        }

    fun addBar() {
        barChartData = barChartData
            .copy(bars = bars.toMutableList().apply {
                add(
                    Bar(
                        label = "Bar${bars.size + 1}",
                        value = randomValue(),
                        color = randomColor()
                    )
                )
            })
    }

    fun removeBar() {
        // Remove last.
        barChartData = barChartData.copy(bars = bars.toMutableList().apply {
            val lastBar = bars[bars.size - 1]
            colors.add(lastBar.color)

            remove(lastBar)
        })
    }

    private fun randomValue(): Float = (100 * Math.random() + 25).toFloat()

    private fun randomColor(): Color {
        val randomIndex = (Math.random() * colors.size).toInt()
        val color = colors[randomIndex]
        colors.removeAt(randomIndex)

        return color
    }
}