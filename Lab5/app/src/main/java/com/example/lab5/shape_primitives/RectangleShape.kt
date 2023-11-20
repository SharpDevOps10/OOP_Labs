package com.example.lab5.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.example.lab5.Shape


open class RectangleShape (paintSettings: Paint) : Shape(paintSettings) {
  override fun draw (canvas: Canvas) {
    configureDrawing()
    if (isEraserMode) defineEraserDrawingStyle()
    val rect = RectF(startXCoordinate, startYCoordinate, endXCoordinate, endYCoordinate)

    canvas.drawRect(rect, paintSettings)
  }

  override fun configureDrawing () {
    super.configureDrawing()
    paintSettings.apply {
      color = Color.BLACK
      style = Paint.Style.STROKE
    }
  }
}