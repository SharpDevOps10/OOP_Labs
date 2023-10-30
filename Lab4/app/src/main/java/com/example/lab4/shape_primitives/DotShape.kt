package com.example.lab4.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab4.Shape

class DotShape (paintSettings: Paint) : Shape(paintSettings) {

  override fun draw (canvas: Canvas) {
    configureDrawing()
    paintSettings.strokeWidth = 15f
    canvas.drawPoint(startXCoordinate, startYCoordinate, paintSettings)
  }

  override fun configureDrawing () {
    paintSettings.apply { color = Color.BLACK }
  }

}