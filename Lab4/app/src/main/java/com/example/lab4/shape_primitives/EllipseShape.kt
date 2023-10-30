package com.example.lab4.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab4.Shape

class EllipseShape (paintSettings: Paint) : Shape(paintSettings) {
  private val paint = Paint()

  override fun draw (canvas: Canvas) {
    if (!isEraserMode) {
      configureFillStyle()
      canvas.drawOval(
        2*startXCoordinate - endXCoordinate,
        2*startYCoordinate - endYCoordinate,
        endXCoordinate,
        endYCoordinate,
        paint,
      )
    }

    configureDrawing()
    canvas.drawOval(
      2*startXCoordinate - endXCoordinate,
      2*startYCoordinate - endYCoordinate,
      endXCoordinate,
      endYCoordinate,
      paint,
    )
  }

  override fun configureDrawing () {
    paint.apply {
      this.color = Color.BLACK
      this.style = Paint.Style.STROKE
      this.strokeWidth = 20f
    }
  }

  private fun configureFillStyle () {
    applyDrawingStyle(Color.rgb(128, 128, 128), Paint.Style.FILL)
  }

  private fun applyDrawingStyle (color: Int, style: Paint.Style) {
    paint.apply {
      this.color = color
      this.style = style
    }
  }
}