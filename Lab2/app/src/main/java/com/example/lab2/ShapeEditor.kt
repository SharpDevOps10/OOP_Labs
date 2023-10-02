package com.example.lab2

import android.graphics.Paint

abstract class ShapeEditor (paintSettings: Paint, shapesList: MutableList<Shape>) : Editor() {
  val paint: Paint = paintSettings
  val shapes: MutableList<Shape> = shapesList

  override fun handleLeftMouseButtonRelease () {

  }

  override fun handleLeftMouseButtonPress (x: Float, y: Float) {

  }

  override fun handleMouseMovement (x: Float, y: Float) {

  }
}