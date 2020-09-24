package com.github.tehras.charts.line.renderer.xaxis

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope

interface XAxisDrawer {
    fun requiredHeight(drawScope: DrawScope): Float

    fun drawAxisLine(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect
    )

    fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        labels: List<String>
    )
}