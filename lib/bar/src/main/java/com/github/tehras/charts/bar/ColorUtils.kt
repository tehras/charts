package com.github.tehras.charts.bar

import androidx.ui.graphics.Color

fun Color.toLegacyInt(): Int {
    return android.graphics.Color.argb(
        (alpha * 255.0f + 0.5f).toInt(),
        (red * 255.0f + 0.5f).toInt(),
        (green * 255.0f + 0.5f).toInt(),
        (blue * 255.0f + 0.5f).toInt()
    )
}