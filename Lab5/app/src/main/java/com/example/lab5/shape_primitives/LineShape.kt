package com.example.lab5.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab5.Shape

open class LineShape (paintSettings: Paint) : Shape(paintSettings) {

  override fun draw (canvas: Canvas) {
    if (!isEraserMode) configureDrawing()
    else defineEraserDrawingStyle()
    canvas.drawLine(startXCoordinate, startYCoordinate, endXCoordinate, endYCoordinate, paintSettings)
  }

  override fun configureDrawing () {
    super.configureDrawing()
    paintSettings.apply {
      color = Color.BLACK
      style = Paint.Style.FILL_AND_STROKE
      strokeWidth = 15f
    }
  }
}