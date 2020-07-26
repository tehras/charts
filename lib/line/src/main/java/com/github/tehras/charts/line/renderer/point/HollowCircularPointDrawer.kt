package com.github.tehras.charts.line.renderer.point

import androidx.ui.geometry.Offset
import androidx.ui.graphics.Canvas
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.graphics.PaintingStyle
import androidx.ui.graphics.drawscope.DrawScope
import androidx.ui.unit.Density
import androidx.ui.unit.Dp
import androidx.ui.unit.dp

data class HollowCircularPointDrawer(
    val diameter: Dp = 8.dp,
    val lineThickness: Dp = 2.dp,
    val color: Color = Color.Blue
) : PointDrawer {

    private val paint = Paint().apply {
        color = this@HollowCircularPointDrawer.color
        style = PaintingStyle.stroke
        isAntiAlias = true
    }

    override fun drawPoint(
        drawScope: DrawScope,
        canvas: Canvas,
        center: Offset
    ) {
        with(drawScope as Density) {
            canvas.drawCircle(
                center = center,
                radius = diameter.toPx() / 2f,
                paint = paint.apply {
                    strokeWidth = lineThickness.toPx()
                }
            )
        }
    }
}