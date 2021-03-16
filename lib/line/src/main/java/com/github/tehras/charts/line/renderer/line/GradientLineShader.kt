package com.github.tehras.charts.line.renderer.line

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

class GradientLineShader(colors: List<Color> = listOf(Color.Blue, Color.Transparent)) : LineShader {
    private val brush = Brush.verticalGradient(colors)
    override fun fillLine(drawScope: DrawScope, canvas: Canvas, fillPath: Path) {
        drawScope.drawPath(
            path = fillPath,
            brush = brush
        )
    }
}
