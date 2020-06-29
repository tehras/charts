package com.github.tehras.charts.line.point

import androidx.ui.geometry.Offset
import androidx.ui.graphics.Canvas
import androidx.ui.graphics.drawscope.DrawScope

object NoPointDrawer : PointDrawer {
    override fun drawPoint(
        drawScope: DrawScope,
        canvas: Canvas,
        center: Offset
    ) {
        // Leave empty on purpose, we do not want to draw anything.
    }
}