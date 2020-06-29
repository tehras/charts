package com.github.tehras.charts.line.point

import androidx.ui.geometry.Offset
import androidx.ui.graphics.Canvas
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.graphics.PaintingStyle
import androidx.ui.graphics.drawscope.DrawScope
import androidx.ui.unit.Density
import androidx.ui.unit.Dp
import androidx.ui.unit.dp


data class FilledPointDrawer(
    val size: Dp = 8.dp,
    val color: Color = Color.Blue
) : PointDrawer {

    private val paint = Paint().apply {
        color = this@FilledPointDrawer.color
        style = PaintingStyle.fill
        isAntiAlias = true
    }

    override fun drawPoint(
        drawScope: DrawScope,
        canvas: Canvas,
        offset: Offset
    ) {
        with(drawScope as Density) {
            canvas.drawCircle(offset, size.toPx() / 2f, paint)
        }
    }
}