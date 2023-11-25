package com.example.lab5

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.PathEffect
import java.math.RoundingMode
import java.text.DecimalFormat

open class Shape (protected var paintSettings : Paint) {
  protected var isEraserMode: Boolean = true
  protected var isHighlightingMode: Boolean = false

  var startXCoordinate: Float = 0f
  var startYCoordinate: Float = 0f

  var endXCoordinate: Float = 0f
  var endYCoordinate: Float = 0f

  fun defineEraserMode (eraserMode: Boolean) {
    isEraserMode = eraserMode
  }

  fun defineHighlightingMode () {
    isHighlightingMode = !isHighlightingMode
  }

  fun defineStartCoordinates (x: Float, y: Float) {
    startXCoordinate = x
    startYCoordinate = y
  }

  fun defineEndCoordinates (x: Float, y: Float) {
    endXCoordinate  = x
    endYCoordinate  = y
  }

  open fun draw (canvas: Canvas) {
    if (isHighlightingMode) {
      paintSettings.apply { color = Color.GREEN }
    }
  }

  open fun configureDrawing () {
    paintSettings.apply {
      pathEffect = PathEffect()
    }
  }

  fun defineEraserDrawingStyle () {
    paintSettings.apply {
      color = Color.BLACK
      style = Paint.Style.STROKE
      pathEffect = DashPathEffect(floatArrayOf(45f, 35f), 0f)
    }
  }

  private fun alignCoordinates (coordinate: Float): String {
    val decimalFormat = DecimalFormat("#.##").apply {
      roundingMode = RoundingMode.DOWN
    }
    return decimalFormat.format(coordinate) + "\t".repeat(5)
  }

  fun receiveHighlightingCondition (): Boolean {
    return isHighlightingMode
  }

  private fun getClassNameString (): String = this::class.java.name + "\t".repeat(5)

  open fun toShapeCoordinate (): ShapeCoordinate {
    return ShapeCoordinate(
      getClassNameString(),
      alignCoordinates(startXCoordinate),
      alignCoordinates(startYCoordinate),
      alignCoordinates(endXCoordinate),
      alignCoordinates(endYCoordinate),
      this)
  }
}