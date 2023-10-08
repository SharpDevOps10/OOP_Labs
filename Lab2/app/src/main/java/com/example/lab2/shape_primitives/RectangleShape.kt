package com.example.lab2.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.example.lab2.Shape

class RectangleShape (private val paintSettings : Paint) : Shape (paintSettings) {
  override fun draw (canvas: Canvas) {
    configureDrawing()
    val rect = RectF(startXCoordinate, startYCoordinate, endXCoordinate, endYCoordinate)
    canvas.drawRect(rect, paintSettings)
  }

  override fun configureDrawing() {
    paintSettings.apply {
      color = Color.BLACK
      style = Paint.Style.STROKE
    }
  }

}