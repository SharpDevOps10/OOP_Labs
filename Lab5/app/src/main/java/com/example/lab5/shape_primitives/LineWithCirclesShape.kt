package com.example.lab5.shape_primitives

import android.graphics.Canvas
import android.graphics.Paint

class LineWithCirclesShape (painSettings: Paint): LineShape(painSettings) {
  private var circleSize = 10f

  init {
    paintSettings.style = Paint.Style.FILL
  }
  override fun draw (canvas: Canvas) {
    super.draw(canvas)
    canvas.drawCircle(startXCoordinate, startYCoordinate, circleSize, paintSettings)
    canvas.drawCircle(endXCoordinate, endYCoordinate, circleSize, paintSettings)
  }

}