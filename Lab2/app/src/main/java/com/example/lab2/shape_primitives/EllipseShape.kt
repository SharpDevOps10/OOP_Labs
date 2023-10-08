package com.example.lab2.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab2.Shape

class EllipseShape (private val paintSettings: Paint) : Shape(paintSettings) {
  override fun draw (canvas: Canvas) {
    if (!isEraserMode) {
      configureFillStyle()
      canvas.drawOval(
        2*startXCoordinate - endXCoordinate,
        2*startYCoordinate - endYCoordinate,
        endXCoordinate,
        endYCoordinate,
        paintSettings,
      )
    }

    configureDrawing()
    canvas.drawOval(
      2*startXCoordinate - endXCoordinate,
      2*startYCoordinate - endYCoordinate,
      endXCoordinate,
      endYCoordinate,
      paintSettings,
    )
  }

  override fun configureDrawing() {
    applyDrawingStyle(Color.BLACK, Paint.Style.STROKE)
  }

  private fun configureFillStyle () {
    applyDrawingStyle(Color.rgb(255, 165, 0), Paint.Style.FILL)
  }

  private fun applyDrawingStyle (color: Int, style: Paint.Style) {
    paintSettings.apply {
      this.color = color
      this.style = style
    }
  }
}