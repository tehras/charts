package com.github.tehras.charts.bar

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.bar.renderer.label.LabelDrawer
import com.github.tehras.charts.bar.renderer.xaxis.XAxisDrawer

internal object BarChartUtils {
    fun axisAreas(
        drawScope: DrawScope,
        totalSize: Size,
        xAxisDrawer: XAxisDrawer,
        labelDrawer: LabelDrawer
    ): Pair<Rect, Rect> = with(drawScope) {
        // yAxis
        val yAxisTop = labelDrawer.requiredAboveBarHeight(drawScope)

        // Either 50dp or 10% of the chart width.
        val yAxisRight = minOf(50.dp.toPx(), size.width * 10f / 100f)

        // xAxis
        val xAxisRight = totalSize.width

        // Measure the size of the text and line.
        val xAxisTop = totalSize.height - xAxisDrawer.requiredHeight(drawScope)

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
        drawScope: DrawScope,
        barDrawableArea: Rect,
        progress: Float,
        labelDrawer: LabelDrawer,
        block: (barArea: Rect, bar: BarChartData.Bar) -> Unit
    ) {
        val totalBars = bars.size
        val widthOfBarArea = barDrawableArea.width / totalBars
        val offsetOfBar = widthOfBarArea * 0.2f

        bars.forEachIndexed { index, bar ->
            val left = barDrawableArea.left + (index * widthOfBarArea)
            val height = barDrawableArea.height

            val barHeight = (height - labelDrawer.requiredAboveBarHeight(drawScope)) * progress

            val barArea = Rect(
                left = left + offsetOfBar,
                top = barDrawableArea.bottom - (bar.value / maxBarValue) * barHeight,
                right = left + widthOfBarArea - offsetOfBar,
                bottom = barDrawableArea.bottom
            )

            block(barArea, bar)
        }
    }
}