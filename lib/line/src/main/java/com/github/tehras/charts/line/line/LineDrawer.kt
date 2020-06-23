package com.github.tehras.charts.line.line

import androidx.ui.graphics.Canvas
import androidx.ui.graphics.Path
import androidx.ui.graphics.drawscope.DrawScope

interface LineDrawer {
    fun drawLine(
        drawScope: DrawScope,
        canvas: Canvas,
        linePath: Path
    )
}