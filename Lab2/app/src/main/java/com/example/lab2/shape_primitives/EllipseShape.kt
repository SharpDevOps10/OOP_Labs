package com.example.lab2.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import com.example.lab2.Shape

class EllipseShape (private val paintSettings: Paint) : Shape(paintSettings) {
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
      resetDashedOutline()
    } else {
      configureDashedOutline()
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
    applyDrawingStyle(Color.rgb(255, 165, 0), Paint.Style.FILL)
  }

  private fun applyDrawingStyle (color: Int, style: Paint.Style) {
    paint.apply {
      this.color = color
      this.style = style
    }
  }

  private fun configureDashedOutline() {
    val dashLength = 10f
    val dashGap = 10f
    val dashEffect = DashPathEffect(floatArrayOf(dashLength, dashGap), 0f)
    paint.pathEffect = dashEffect
  }

  private fun resetDashedOutline() {
    paint.pathEffect = null
  }
}