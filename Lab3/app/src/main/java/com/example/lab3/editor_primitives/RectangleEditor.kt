package com.example.lab3.editor_primitives

import android.graphics.Paint
import com.example.lab3.Shape
import com.example.lab3.ShapeEditor
import com.example.lab3.shape_primitives.RectangleShape

class RectangleEditor (private val paintSettings: Paint, private val shapesList: MutableList<Shape>) : ShapeEditor(paintSettings, shapesList) {
  private var rectShape: RectangleShape? = null
  private var centerX: Float = 0f
  private var centerY: Float = 0f


  override fun onTouchDown(x: Float, y: Float) {
    centerX = x
    centerY = y
    rectShape = RectangleShape(paintSettings)
  }

  override fun onTouchUp() {
    rectShape?.let {
      addShapeToEditor(it, shapesList)
      it.toggleDashed()
    }
    rectShape = null
  }

  override fun handleMouseMovement(x: Float, y: Float) {
    rectShape?.let {
      shapesList.remove(it)
      val left = centerX - (x - centerX)
      val top = centerY - (y - centerY)
      val right = centerX + (x - centerX)
      val bottom = centerY + (y - centerY)
      it.defineStartCoordinates(left, top)
      it.defineEndCoordinates(right, bottom)
      addShapeToEditor(it, shapesList)
    }
  }
}