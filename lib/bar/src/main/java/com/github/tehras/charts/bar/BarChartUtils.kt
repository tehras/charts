package com.github.tehras.charts.bar

import androidx.ui.geometry.Rect
import androidx.ui.geometry.Size
import androidx.ui.unit.Density
import androidx.ui.unit.dp
import com.github.tehras.charts.bar.BarChartData.LabelFormat.DrawLocation.*

internal object BarChartUtils {
    fun axisAreas(
        barChartData: BarChartData,
        totalSize: Size,
        density: Density
    ): Pair<Rect, Rect> {
        // yAxis
        val yAxisTop = 0f

        val yAxisRight = if (barChartData.yAxis.isEnabled) {
            minOf(100.dp.value, totalSize.width * 0.15f)
        } else {
            0f
        }

        // xAxis
        val xAxisRight = totalSize.width

        // Measure the size of the text and line.
        val xAxisTop = if (barChartData.xAxis.isEnabled) {
            val needSpaceForXAxisLabel = barChartData.valueLabelFormat.drawLocation == XAxis
            val lineHeight = barChartData.xAxis.axisLine.thickness.value
            val xAxisLabelSpace = if (needSpaceForXAxisLabel) {
                (barChartData.valueLabelFormat.textSize.value * density.fontScale) * 3f / 2f
            } else {
                0f
            }

            totalSize.height - lineHeight - xAxisLabelSpace
        } else {
            totalSize.height
        }

        return Pair(
            Rect(yAxisRight, xAxisTop, xAxisRight, totalSize.height),
            Rect(0f, yAxisTop, yAxisRight, xAxisTop)
        )
    }

    fun barDrawableArea(xAxisArea: Rect): Rect {
        return Rect(
            left = xAxisArea.left,
            top = 0f,
            right = xAxisArea.right,
            bottom = xAxisArea.top
        )
    }

    fun BarChartData.forEachWithArea(
        density: Density,
        barDrawableArea: Rect,
        progress: Float,
        block: (barArea: Rect, bar: BarChartData.Bar) -> Unit
    ) {
        val totalBars = bars.size
        val widthOfBarArea = barDrawableArea.width / totalBars
        val offsetOfBar = widthOfBarArea * 0.2f

        bars.forEachIndexed { index, bar ->
            val left = barDrawableArea.left + (index * widthOfBarArea)
            val height = barDrawableArea.height

            val barHeight = (height - ((topOffset(density) / 100f) * height)) * progress

            val barArea = Rect(
                left = left + offsetOfBar,
                top = barDrawableArea.bottom - (bar.value / maxBarValue) * barHeight,
                right = left + widthOfBarArea - offsetOfBar,
                bottom = barDrawableArea.bottom
            )

            block(barArea, bar)
        }
    }

    fun BarChartData.topOffset(density: Density) =
        when (valueLabelFormat.drawLocation) {
            Outside -> with(density) { 3f / 2f * valueLabelFormat.textSize.toPx() }
            Inside,
            XAxis -> 0f
        }
}