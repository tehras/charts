package com.github.tehras.charts.piechart

import androidx.ui.geometry.Rect
import androidx.ui.geometry.Size

object PieChartUtils {
    internal fun calculateDrawableArea(
        size: Size,
        pieChartData: PieChartData
    ): Rect {
        val sliceThicknessOffset =
            calculateSectorThickness(size = size, pieChartData = pieChartData) / 2f
        val offsetHorizontally = (size.width - size.height) / 2f

        return Rect(
            left = sliceThicknessOffset + offsetHorizontally,
            top = sliceThicknessOffset,
            right = size.width - sliceThicknessOffset - offsetHorizontally,
            bottom = size.height - sliceThicknessOffset
        )
    }

    internal  fun calculateSectorThickness(size: Size, pieChartData: PieChartData): Float {
        val minSize = minOf(size.width, size.height)

        return minSize * (pieChartData.sliceThickness / 200f)
    }

    internal  fun calculateAngle(
        sliceLength: Float,
        totalLength: Float,
        progress: Float
    ): Float {
        return 360.0f * (sliceLength * progress) / totalLength
    }
}