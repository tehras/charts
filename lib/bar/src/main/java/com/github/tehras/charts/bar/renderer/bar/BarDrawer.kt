package com.github.tehras.charts.bar.renderer.bar

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.github.tehras.charts.bar.BarChartData

interface BarDrawer {
  fun drawBar(
    drawScope: DrawScope,
    canvas: Canvas,
    barArea: Rect,
    bar: BarChartData.Bar
  )
}