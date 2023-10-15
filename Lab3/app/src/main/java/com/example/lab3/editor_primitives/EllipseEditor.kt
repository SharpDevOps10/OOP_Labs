package com.example.lab3.editor_primitives

import android.graphics.Paint
import com.example.lab3.Shape
import com.example.lab3.ShapeEditor
import com.example.lab3.shape_primitives.EllipseShape

class EllipseEditor (private val paintSettings: Paint, private val shapesList: MutableList<Shape>) : ShapeEditor(paintSettings, shapesList) {
  private var ellipseShape: EllipseShape? = null

  override fun onTouchDown (x: Float, y: Float) {
    ellipseShape = EllipseShape(paintSettings)
    ellipseShape?.defineStartCoordinates(x, y)
  }

  override fun onTouchUp () {
    ellipseShape?.let {
      if (shapesList.contains(it)) shapesList.remove(it)
      it.defineEraserMode(false)
      addShapeToEditor(it, shapesList)
    }
    ellipseShape = null
  }

  override fun handleMouseMovement (x: Float, y: Float) {
    ellipseShape?.let {
      if (shapesList.contains(it)) shapesList.remove(it)
      it.defineEndCoordinates(x, y)
      addShapeToEditor(it, shapesList)
    }
  }
}