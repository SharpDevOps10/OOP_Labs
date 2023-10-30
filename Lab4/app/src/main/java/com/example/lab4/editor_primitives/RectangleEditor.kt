package com.example.lab4.editor_primitives

import android.graphics.Paint
import com.example.lab4.Shape
import com.example.lab4.ShapeEditor
import com.example.lab4.shape_primitives.RectangleShape

class RectangleEditor (private val paintSettings: Paint, private val shapesList: MutableList<Shape>) : ShapeEditor(paintSettings, shapesList) {
  private var rectShape: RectangleShape? = null


  override fun onTouchDown (x: Float, y: Float) {
    rectShape = RectangleShape(paintSettings)
    rectShape!!.defineStartCoordinates(x, y)
  }

  override fun onTouchUp () {
    rectShape?.let {
      addShapeToEditor(it, shapesList)
    }
    rectShape = null
  }

  override fun handleMouseMovement (x: Float, y: Float) {
    rectShape?.let {
      shapesList.remove(it)

      it.defineEndCoordinates(x, y)
      addShapeToEditor(it, shapesList)
    }
  }
}