package com.github.tehras.charts.line.point

import androidx.ui.geometry.Offset
import androidx.ui.graphics.Canvas
import androidx.ui.graphics.drawscope.DrawScope

interface PointDrawer {
    fun drawPoint(
        drawScope: DrawScope,
        canvas: Canvas,
        offset: Offset
    )
}
