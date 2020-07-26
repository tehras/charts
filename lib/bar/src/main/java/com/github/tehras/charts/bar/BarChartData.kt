package com.github.tehras.charts.bar

import androidx.ui.graphics.Color

data class BarChartData(
    val bars: List<Bar>,
    val padBy: Float = 20f,
    val startAtZero: Boolean = false
) {
    init {
        require(padBy in 0f..100f)
    }

    private val yMinMax: Pair<Float, Float>
        get() {
            val min = bars.minByOrNull { it.value }?.value ?: 0f
            val max = bars.maxByOrNull { it.value }?.value ?: 0f

            return min to max
        }
    internal val maxYValue: Float =
        yMinMax.second + ((yMinMax.second - yMinMax.first) * padBy / 100f)
    internal val minYValue: Float
        get() {
            return if (startAtZero) {
                0f
            } else {
                yMinMax.first - ((yMinMax.second - yMinMax.first) * padBy / 100f)
            }
        }

    val maxBarValue = bars.maxByOrNull { it.value }?.value ?: 0f

    data class Bar(
        val value: Float,
        val color: Color,
        val label: String
    )
}
