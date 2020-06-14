package com.github.tehras.charts.piechart

import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.graphics.PaintingStyle

data class PieChartData(
    val slices: List<Slice>,
    val sliceThickness: Float = 0f
) {
    init {
        require(sliceThickness in 10f..100f) {
            "Thickness of $sliceThickness must be between 10-100"
        }
    }

    internal val totalSize: Float
        get() {
            var total = 0f
            slices.forEach { total += it.value }
            return total
        }
    internal val sectionPaint = Paint().apply {
        isAntiAlias = true
        style = PaintingStyle.stroke
    }

    data class Slice(
        val value: Float,
        val color: Color
    )
}