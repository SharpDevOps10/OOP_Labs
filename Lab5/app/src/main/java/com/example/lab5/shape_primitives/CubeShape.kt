package com.example.lab5.shape_primitives

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.abs

class CubeShape (paintSettings: Paint): RectangleShape(paintSettings) {
  override fun draw(canvas: Canvas) {
    if (!isEraserMode) configureDrawing()
    else defineEraserDrawingStyle()

    val halfWidth = abs(startXCoordinate - endXCoordinate) / 2
    val heightDifference = abs(startYCoordinate - endYCoordinate)
    if (isHighlightingMode) paintSettings.apply { color = Color.GREEN }
    val topLeftX = 2 * startXCoordinate - endXCoordinate
    val topLeftY = 2 * startYCoordinate - endYCoordinate

    drawRect(canvas, topLeftX, topLeftY, endXCoordinate, endYCoordinate)
    drawRect(canvas, topLeftX + halfWidth, topLeftY - heightDifference, endXCoordinate + halfWidth, endYCoordinate - heightDifference)
    drawLines(canvas, topLeftX, topLeftY, endXCoordinate, endYCoordinate, halfWidth, heightDifference)

  }

  private fun drawRect (canvas: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
    canvas.drawRect(left, top, right, bottom, paintSettings)
  }

  private fun drawLines (canvas: Canvas, x1: Float, y1: Float, x2: Float, y2: Float, halfWidth: Float, heightDifference: Float) {
    canvas.drawLine(x1, y1, x1 + halfWidth, y1 - heightDifference, paintSettings)
    canvas.drawLine(x2, y2, x2 + halfWidth, y2 - heightDifference, paintSettings)
    canvas.drawLine(x1, y2, x1 + halfWidth, y2 - heightDifference, paintSettings)
    canvas.drawLine(x2, y1, x2 + halfWidth, y1 - heightDifference, paintSettings)
  }
}