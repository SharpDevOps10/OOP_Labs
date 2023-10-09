package com.example.lab2.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import com.example.lab2.Shape

class LineShape(paintSettings: Paint) : Shape(paintSettings) {
  private var isDashed = true
  private val paint = Paint()

  fun toggleDashed () {
    isDashed = !isDashed
  }

  override fun draw (canvas: Canvas) {
    configureDrawing()
    if (isDashed) {
      paint.pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
    } else {
      paint.pathEffect = null
    }
    canvas.drawLine(startXCoordinate, startYCoordinate, endXCoordinate, endYCoordinate, paint)
  }

  override fun configureDrawing () {
    paint.apply {
      color = Color.BLACK
      style = Paint.Style.FILL_AND_STROKE
      strokeWidth = 20f
    }
  }
}