package com.example.lab4

import android.graphics.Paint

abstract class ShapeEditor (paintSettings: Paint, shapesList: MutableList<Shape>) : Editor() {
  val paint: Paint = paintSettings
  val shapes: MutableList<Shape> = shapesList
  private val shapesLimit: Int = 124
  protected fun addShapeToEditor (shape: Shape, shapes: MutableList<Shape>) {
    if (shapes.lastIndex == shapesLimit - 1) {
      shapes.removeAt(shapes.lastIndex)
    }
    shapes.add(shape)
  }
  override fun onTouchUp () {}

  override fun onTouchDown (x: Float, y: Float) {}

  override fun handleMouseMovement (x: Float, y: Float) {}
}