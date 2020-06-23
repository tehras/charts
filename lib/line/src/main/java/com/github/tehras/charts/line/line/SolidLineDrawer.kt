package com.github.tehras.charts.line.line

import androidx.ui.graphics.*
import androidx.ui.graphics.drawscope.DrawScope
import androidx.ui.unit.Dp
import androidx.ui.unit.dp

data class SolidLineDrawer(
    val thickness: Dp = 3.dp,
    val color: Color = Color.Cyan
) : LineDrawer {
    private val paint = Paint().apply {
        this.color = this@SolidLineDrawer.color
        this.style = PaintingStyle.stroke
        this.isAntiAlias = true
    }

    override fun drawLine(
        drawScope: DrawScope,
        canvas: Canvas,
        linePath: Path
    ) {
        val lineThickness = with(drawScope) {
            thickness.toPx().value
        }

        canvas.drawPath(
            path = linePath,
            paint = paint.apply {
                strokeWidth = lineThickness
            }
        )
    }
}