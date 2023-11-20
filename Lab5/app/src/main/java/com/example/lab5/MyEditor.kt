package com.example.lab5

import android.graphics.Paint


class MyEditor private constructor (private val painSettings: Paint, private val shapesList: MutableList<Shape>) {
  private var initialShape = Shape(painSettings)
  private val shapesLimit: Int = 123

  companion object {
    private var instance: MyEditor? = null
    fun getInstance (painSettings: Paint, shapesList: MutableList<Shape>): MyEditor {
      if (instance == null) instance = MyEditor(painSettings, shapesList)
      return instance!!
    }
  }

  fun onTouchDown (x: Float, y: Float) {
    defineInitialShape(initialShape)
    initialShape.defineStartCoordinates(x, y)
  }

  fun onTouchUp () {
    initialShape.let {
      if (shapesList.contains(it)) shapesList.remove(it)
      it.defineEraserMode(false)
      addShapeToEditor(it, shapesList)
    }
  }

  fun handleMouseMovement (x: Float, y: Float) {
    initialShape.let {
      if (shapesList.contains(it)) shapesList.remove(it)
      it.defineEndCoordinates(x, y)
      addShapeToEditor(it, shapesList)
    }
  }

  fun defineInitialShape (shape: Shape) {
    initialShape = shape.javaClass.getDeclaredConstructor(Paint::class.java).newInstance(painSettings)
  }

  private fun addShapeToEditor (shape: Shape, shapes: MutableList<Shape>) {
    if (shapes.lastIndex == shapesLimit - 1) {
      shapes.removeAt(shapes.lastIndex)
    }
    shapes.add(shape)
  }
}