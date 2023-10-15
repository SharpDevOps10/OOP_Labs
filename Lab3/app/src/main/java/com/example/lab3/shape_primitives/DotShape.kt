package com.example.lab3.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab3.Shape

class DotShape (paintSettings: Paint) : Shape(paintSettings) {
  private val paint: Paint = paintSettings

  override fun draw (canvas: Canvas) {
    configureDrawing()
    paint.strokeWidth = 20f
    canvas.drawPoint(startXCoordinate, startYCoordinate, paint)
  }

  override fun configureDrawing () {
    paint.apply { color = Color.BLACK }
  }

}