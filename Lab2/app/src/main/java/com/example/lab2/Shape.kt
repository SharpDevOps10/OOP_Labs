package com.example.lab2

import android.graphics.Canvas
import android.graphics.Paint

abstract class Shape (paintSettings : Paint) {
  private var isEraserMode: Boolean = true

  private var startXCoordinate: Float = 0f
  private var startYCoordinate: Float = 0f

  private var endXCoordinate: Float = 0f
  private var endYCoordinate: Float = 0f

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