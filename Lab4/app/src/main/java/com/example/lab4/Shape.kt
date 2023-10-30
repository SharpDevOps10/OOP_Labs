package com.example.lab4

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.PathEffect

open class Shape (protected var paintSettings : Paint) {
  protected var isEraserMode: Boolean = true

  var startXCoordinate: Float = 0f
  var startYCoordinate: Float = 0f

  var endXCoordinate: Float = 0f
  var endYCoordinate: Float = 0f

  fun defineEraserMode (eraserMode: Boolean) {
    isEraserMode = eraserMode
  }

  fun defineStartCoordinates (x: Float, y: Float) {
    startXCoordinate = x
    startYCoordinate = y
  }

  fun defineEndCoordinates (x: Float, y: Float) {
    endXCoordinate  = x
    endYCoordinate  = y
  }

  open fun draw (canvas: Canvas) {}

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
}