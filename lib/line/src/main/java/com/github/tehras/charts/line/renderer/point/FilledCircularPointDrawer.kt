package com.github.tehras.charts.line.renderer.point

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class FilledCircularPointDrawer(
    val diameter: Dp = 8.dp,
    val color: Color = Color.Blue
) : PointDrawer {

    private val paint = Paint().apply {
        color = this@FilledCircularPointDrawer.color
        style = PaintingStyle.Fill
        isAntiAlias = true
    }

    override fun drawPoint(
        drawScope: DrawScope,
        canvas: Canvas,
        center: Offset
    ) {
        with(drawScope as Density) {
            canvas.drawCircle(center, diameter.toPx() / 2f, paint)
        }
    }
}