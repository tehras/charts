package com.github.tehras.charts.bar.renderer.label

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer.DrawLocation.Inside
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer.DrawLocation.Outside
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer.DrawLocation.XAxis
import com.github.tehras.charts.piechart.utils.toLegacyInt

class SimpleValueDrawer(
  private val drawLocation: DrawLocation = Inside,
  private val labelTextSize: TextUnit = 12.sp,
  private val labelTextColor: Color = Color.Black
) : LabelDrawer {
  private val _labelTextArea: Float? = null
  private val paint = android.graphics.Paint().apply {
    this.textAlign = android.graphics.Paint.Align.CENTER
    this.color = labelTextColor.toLegacyInt()
  }

  override fun requiredAboveBarHeight(drawScope: DrawScope): Float = when (drawLocation) {
    Outside -> (3f / 2f) * labelTextHeight(drawScope)
    Inside,
    XAxis -> 0f
  }

  override fun requiredXAxisHeight(drawScope: DrawScope): Float = when (drawLocation) {
    XAxis -> labelTextHeight(drawScope)
    Inside,
    Outside -> 0f
  }

  override fun drawLabel(
    drawScope: DrawScope,
    canvas: Canvas,
    label: String,
    barArea: Rect,
    xAxisArea: Rect
  ) = with(drawScope) {
    val xCenter = barArea.left + (barArea.width / 2)

    val yCenter = when (drawLocation) {
      Inside -> (barArea.top + barArea.bottom) / 2
      Outside -> (barArea.top) - labelTextSize.toPx() / 2
      XAxis -> barArea.bottom + labelTextHeight(drawScope)
    }

    canvas.nativeCanvas.drawText(label, xCenter, yCenter, paint(drawScope))
  }

  private fun labelTextHeight(drawScope: DrawScope) = with(drawScope) {
    _labelTextArea ?: ((3f / 2f) * labelTextSize.toPx())
  }

  private fun paint(drawScope: DrawScope) = with(drawScope) {
    paint.apply {
      this.textSize = labelTextSize.toPx()
    }
  }

  enum class DrawLocation {
    Inside,
    Outside,
    XAxis
  }
}