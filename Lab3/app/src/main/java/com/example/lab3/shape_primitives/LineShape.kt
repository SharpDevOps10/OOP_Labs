package com.example.lab3.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab3.Shape

class LineShape (paintSettings: Paint) : Shape(paintSettings) {
  private val paint = Paint()

  override fun draw (canvas: Canvas) {
    configureDrawing()
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