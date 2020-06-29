package com.github.tehras.charts.piechart

import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.graphics.PaintingStyle
import androidx.ui.unit.Density
import androidx.ui.unit.Dp
import androidx.ui.unit.dp

data class AxisLine(
    val thickness: Dp = 1.5.dp,
    val color: Color = Color.Gray
) {
    private val paint = Paint().apply {
        this.color = this@AxisLine.color
        this.style = PaintingStyle.stroke
    }

    fun paint(density: Density) = paint.apply {
        this.strokeWidth = thickness.value * density.density
    }
}
