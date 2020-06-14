package com.github.tehras.charts.piechart

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Canvas
import androidx.ui.geometry.Rect
import androidx.ui.geometry.Size
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.drawCanvas
import androidx.ui.layout.fillMaxSize
import androidx.ui.tooling.preview.Preview

@Composable
fun PieChart(
    pieChartData: PieChartData,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    AnimatedChart { progress ->
        Canvas(modifier = modifier, onCanvas = {
            drawCanvas { canvas, size ->
                val rect = calculateDrawableArea(size, pieChartData)
                var startArc = 0f

                pieChartData.slices.forEach { slice ->
                    val arc =
                        calculateAngle(
                            sliceLength = slice.value,
                            totalLength = pieChartData.totalSize,
                            progress = progress
                        )

                    canvas.drawArc(
                        rect = rect,
                        paint = pieChartData.sectionPaint.apply {
                            color = slice.color
                            strokeWidth = calculateSectorThickness(
                                size = size,
                                pieChartData = pieChartData
                            )
                        },
                        startAngle = startArc,
                        sweepAngle = arc,
                        useCenter = false
                    )

                    startArc += arc
                }
            }
        })
    }
}

@Preview
@Composable
fun PieChartPreview() = PieChartData(
    slices = listOf(
        PieChartData.Slice(25f, Color.Red),
        PieChartData.Slice(42f, Color.Blue),
        PieChartData.Slice(23f, Color.Green)
    )
)

private fun calculateDrawableArea(
    size: Size,
    pieChartData: PieChartData
): Rect {
    val sliceThicknessOffset =
        calculateSectorThickness(size = size, pieChartData = pieChartData) / 2f
    val offsetHorizontally = (size.width - size.height) / 2f

    return Rect(
        left = sliceThicknessOffset + offsetHorizontally,
        top = sliceThicknessOffset,
        right = size.width - sliceThicknessOffset - offsetHorizontally,
        bottom = size.height - sliceThicknessOffset
    )
}

private fun calculateSectorThickness(size: Size, pieChartData: PieChartData): Float {
    val minSize = minOf(size.width, size.height)

    return minSize * (pieChartData.sliceThickness / 200f)
}

private fun calculateAngle(
    sliceLength: Float,
    totalLength: Float,
    progress: Float
): Float {
    return 360.0f * (sliceLength * progress) / totalLength
}