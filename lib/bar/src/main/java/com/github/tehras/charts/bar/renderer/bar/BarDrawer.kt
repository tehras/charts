package com.github.tehras.charts.bar.renderer.bar

import androidx.ui.geometry.Rect
import androidx.ui.graphics.Canvas
import androidx.ui.graphics.drawscope.DrawScope
import com.github.tehras.charts.bar.BarChartData

interface BarDrawer {
    fun drawBar(
        drawScope: DrawScope,
        canvas: Canvas,
        barArea: Rect,
        bar: BarChartData.Bar
    )
}