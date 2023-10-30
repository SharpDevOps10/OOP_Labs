package com.example.lab3

import android.graphics.Canvas
import android.graphics.Paint

abstract class Shape (paintSettings : Paint) {
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

  abstract fun draw (canvas: Canvas)

  abstract fun configureDrawing ()

}