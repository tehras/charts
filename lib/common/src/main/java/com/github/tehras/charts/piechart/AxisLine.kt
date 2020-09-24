package com.github.tehras.charts.piechart

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AxisLine(
    val thickness: Dp = 1.5.dp,
    val color: Color = Color.Gray
) {
    private val paint = Paint().apply {
        this.color = this@AxisLine.color
        this.style = PaintingStyle.Stroke
    }

    fun paint(density: Density) = paint.apply {
        this.strokeWidth = thickness.value * density.density
    }
}
