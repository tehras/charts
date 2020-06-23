package com.github.tehras.charts.line


data class LineChartData(
    val points: List<Point>,
    /** This is percentage we pad yValue by.**/
    val padBy: Float = 20f,
    val startAtZero: Boolean = false
) {
    init {
        require(padBy in 0f..100f)
    }

    private val yMinMax: Pair<Float, Float>
        get() {
            val min = points.minBy { it.value }?.value ?: 0f
            val max = points.maxBy { it.value }?.value ?: 0f

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
    internal val yRange = maxYValue - minYValue

    data class Point(val value: Float, val label: String)
}