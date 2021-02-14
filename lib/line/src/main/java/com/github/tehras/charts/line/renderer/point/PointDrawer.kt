package com.github.tehras.charts.line.renderer.point

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope

interface PointDrawer {
  fun drawPoint(
    drawScope: DrawScope,
    canvas: Canvas,
    center: Offset
  )
}
