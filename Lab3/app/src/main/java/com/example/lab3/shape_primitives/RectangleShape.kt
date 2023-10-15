package com.example.lab3.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.RectF
import com.example.lab3.Shape

class RectangleShape (initialPaintSettings: Paint) : Shape(initialPaintSettings) {
  private var isDashed = true
  private val paint = Paint()

  fun toggleDashed () {
    isDashed = !isDashed
  }

  override fun draw (canvas: Canvas) {
    configureDrawing()
    val rect = RectF(startXCoordinate, startYCoordinate, endXCoordinate, endYCoordinate)

    if (isDashed) {
      paint.pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
    } else {
      paint.pathEffect = null
    }

    canvas.drawRect(rect, paint)
  }

  override fun configureDrawing () {
    paint.apply {
      color = Color.BLACK
      style = Paint.Style.STROKE
      strokeWidth = 20f
    }
  }
}