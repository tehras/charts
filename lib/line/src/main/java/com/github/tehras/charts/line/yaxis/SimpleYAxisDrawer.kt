package com.github.tehras.charts.line.yaxis

import androidx.ui.geometry.Offset
import androidx.ui.geometry.Rect
import androidx.ui.graphics.Canvas
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.graphics.PaintingStyle
import androidx.ui.graphics.drawscope.DrawScope
import androidx.ui.unit.Dp
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.github.tehras.charts.piechart.utils.toLegacyInt
import kotlin.math.max
import kotlin.math.roundToInt

typealias LabelFormatter = (value: Float) -> String

class SimpleYAxisDrawer(
    private val labelTextSize: TextUnit = 12.sp,
    private val labelTextColor: Color = Color.Black,
    private val labelRatio: Int = 3,
    private val labelValueFormatter: LabelFormatter = { value -> "%.1f".format(value) },
    private val axisLineThickness: Dp = 1.dp,
    private val axisLineColor: Color = Color.Black
) : YAxisDrawer {
    private val axisLinePaint = Paint().apply {
        isAntiAlias = true
        color = axisLineColor
        style = PaintingStyle.stroke
    }
    private val textPaint = android.graphics.Paint().apply {
        isAntiAlias = true
        color = labelTextColor.toLegacyInt()
    }

    override fun drawAxisLine(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect
    ) {
        with(drawScope) {
            val lineThickness = axisLineThickness.toPx().value
            val dx = drawableArea.right - (lineThickness / 2f)

            canvas.drawLine(
                p1 = Offset(
                    dx = dx,
                    dy = drawableArea.top
                ),
                p2 = Offset(
                    dx = dx,
                    dy = drawableArea.bottom
                ),
                paint = axisLinePaint.apply {
                    strokeWidth = lineThickness
                }
            )
        }
    }

    override fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        minValue: Float,
        maxValue: Float,
        padBy: Float
    ) {
        with(drawScope) {
            val labelPaint = textPaint.apply {
                textSize = labelTextSize.toPx().value
                textAlign = android.graphics.Paint.Align.RIGHT
            }
            val minLabelHeight = (labelTextSize.toPx().value * labelRatio.toFloat())
            val padOffset = (padBy / 100f) * (maxValue - minValue) / 2f
            val heightOffset = (drawableArea.height * padBy) / 200f
            val allowedHeight = ((drawableArea.height * (100f - padBy)) / 100f)
            val labelCount = max((drawableArea.height / minLabelHeight).roundToInt(), 2)

            for (i in 0..labelCount) {
                val value = minValue + (i * (((maxValue - minValue) + padOffset) / labelCount))

                val label = labelValueFormatter(value)
                val dx =
                    drawableArea.right - axisLineThickness.toPx().value - (labelTextSize.toPx().value / 2f)
                val dy =
                    drawableArea.bottom - heightOffset - (i * (allowedHeight / labelCount))


                canvas.nativeCanvas.drawText(label, dx, dy, labelPaint)
            }
        }
    }

}