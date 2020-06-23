package com.github.tehras.charts.line

import androidx.ui.geometry.Offset
import androidx.ui.geometry.Rect
import androidx.ui.geometry.Size
import androidx.ui.graphics.Path
import androidx.ui.unit.Density
import androidx.ui.unit.dp

object LineChartUtils {
    fun calculateDrawableArea(
        xAxisDrawableArea: Rect,
        yAxisDrawableArea: Rect,
        size: Size,
        offset: Float
    ): Rect {
        val horizontalOffset = xAxisDrawableArea.width * offset / 100f

        return Rect(
            left = yAxisDrawableArea.right + horizontalOffset,
            top = 0f,
            bottom = xAxisDrawableArea.top,
            right = size.width - horizontalOffset
        )
    }

    fun calculateXAxisDrawableArea(
        yAxisWidth: Float,
        labelHeight: Float,
        size: Size
    ): Rect {
        val top = size.height - labelHeight

        return Rect(
            left = yAxisWidth,
            top = top,
            bottom = size.height,
            right = size.width
        )
    }

    fun calculateXAxisLabelsDrawableArea(
        xAxisDrawableArea: Rect,
        offset: Float
    ): Rect {
        val horizontalOffset = xAxisDrawableArea.width * offset / 100f

        return Rect(
            left = xAxisDrawableArea.left + horizontalOffset,
            top = xAxisDrawableArea.top,
            bottom = xAxisDrawableArea.bottom,
            right = xAxisDrawableArea.right - horizontalOffset
        )
    }

    fun Density.calculateYAxisDrawableArea(
        xAxisLabelSize: Float,
        size: Size
    ): Rect {
        // Either 50dp or 10% of the chart width.
        val right = minOf(50.dp.toPx().value, size.width * 10f / 100f)

        return Rect(
            left = 0f,
            top = 0f,
            bottom = size.height - xAxisLabelSize,
            right = right
        )
    }

    fun calculatePointLocation(
        drawableArea: Rect,
        lineChartData: LineChartData,
        point: LineChartData.Point,
        index: Int
    ): Offset {
        val x = (index.toFloat() / (lineChartData.points.size - 1))
        val y = ((point.value - lineChartData.minYValue) / lineChartData.yRange)

        return Offset(
            dx = (x * drawableArea.width) + drawableArea.left,
            dy = drawableArea.height - (y * drawableArea.height)
        )
    }

    fun calculateLinePath(
        drawableArea: Rect,
        lineChartData: LineChartData
    ): Path = Path().apply {
        lineChartData.points.forEachIndexed { index, point ->
            val pointLocation = calculatePointLocation(
                drawableArea = drawableArea,
                lineChartData = lineChartData,
                point = point,
                index = index
            )

            // First point we want the path to move to.
            if (index == 0) {
                moveTo(pointLocation.dx, pointLocation.dy)
            } else {
                // Otherwise we just line to.
                lineTo(pointLocation.dx, pointLocation.dy)
            }
        }
    }
}