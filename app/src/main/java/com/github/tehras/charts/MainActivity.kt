package com.github.tehras.charts

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.github.tehras.charts.ui.ChartsApp

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    this.setContent {
      ChartsApp()
    }
  }
}