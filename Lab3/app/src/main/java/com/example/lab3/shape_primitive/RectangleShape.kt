package com.example.lab3.shape_primitive

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.example.lab3.Shape

class RectangleShape (initialPaintSettings: Paint) : Shape(initialPaintSettings) {
  private val paint = Paint()

  override fun draw (canvas: Canvas) {
    configureDrawing()
    val rect = RectF(startXCoordinate, startYCoordinate, endXCoordinate, endYCoordinate)

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