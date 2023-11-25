package com.example.lab5.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab5.Shape

class DotShape (paintSettings: Paint) : Shape(paintSettings) {

  override fun draw (canvas: Canvas) {
    configureDrawing()
    super.draw(canvas)
    paintSettings.strokeWidth = 10f
    canvas.drawPoint(startXCoordinate, startYCoordinate, paintSettings)
  }

  override fun configureDrawing () {
    paintSettings.apply { color = Color.BLACK }
  }

}