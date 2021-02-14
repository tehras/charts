package com.github.tehras.charts.line.renderer.line

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class SolidLineDrawer(
  val thickness: Dp = 3.dp,
  val color: Color = Color.Cyan
) : LineDrawer {
  private val paint = Paint().apply {
    this.color = this@SolidLineDrawer.color
    this.style = PaintingStyle.Stroke
    this.isAntiAlias = true
  }

  override fun drawLine(
    drawScope: DrawScope,
    canvas: Canvas,
    linePath: Path
  ) {
    val lineThickness = with(drawScope) {
      thickness.toPx()
    }

    canvas.drawPath(
      path = linePath,
      paint = paint.apply {
        strokeWidth = lineThickness
      }
    )
  }
}