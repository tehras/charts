package com.github.tehras.charts.line.renderer.yaxis

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope

interface YAxisDrawer {
    fun drawAxisLine(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect
    )

    fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        minValue: Float,
        maxValue: Float
    )
}