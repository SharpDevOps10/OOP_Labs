package com.example.lab2.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab2.Shape

class LineShape (private val paintSettings: Paint) : Shape(paintSettings) {
  override fun draw (canvas: Canvas) {
    configureDrawing()
    canvas.drawLine(startXCoordinate, startYCoordinate, endXCoordinate, endYCoordinate, paintSettings)
  }

  override fun configureDrawing () {
    paintSettings.apply {
      color = Color.BLACK
      style = Paint.Style.FILL_AND_STROKE
    }
  }

}