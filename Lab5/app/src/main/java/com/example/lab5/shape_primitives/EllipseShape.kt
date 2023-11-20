package com.example.lab5.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.example.lab5.Shape

class EllipseShape (paintSettings: Paint) : Shape(paintSettings) {
  override fun draw(canvas: Canvas) {
    val left = 2 * startXCoordinate - endXCoordinate
    val top = 2 * startYCoordinate - endYCoordinate
    val right = endXCoordinate
    val bottom = endYCoordinate

    val ovalRect = RectF(left, top, right, bottom)

    if (!isEraserMode) {
      configureFillStyle()
      canvas.drawOval(ovalRect, paintSettings)
      configureDrawing()
      canvas.drawOval(ovalRect, paintSettings)
    } else {
      defineEraserDrawingStyle()
      canvas.drawOval(ovalRect, paintSettings)
    }
  }

  override fun configureDrawing () {
    super.configureDrawing()
    paintSettings.apply {
      this.color = Color.BLACK
      this.style = Paint.Style.STROKE
      this.strokeWidth = 15f
    }
  }

  private fun configureFillStyle () {
    applyDrawingStyle(Color.rgb(128, 128, 128), Paint.Style.FILL)
  }

  private fun applyDrawingStyle (color: Int, style: Paint.Style) {
    paintSettings.apply {
      this.color = color
      this.style = style
    }
  }
}