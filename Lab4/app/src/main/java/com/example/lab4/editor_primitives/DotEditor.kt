package com.example.lab4.editor_primitives

import android.graphics.Paint
import com.example.lab4.Shape
import com.example.lab4.ShapeEditor
import com.example.lab4.shape_primitives.DotShape

class DotEditor (paintSettings: Paint, shapesList: MutableList<Shape>) : ShapeEditor(paintSettings, shapesList) {
  private var currentDotShape: DotShape? = null

  override fun onTouchDown (x: Float, y: Float) {
    currentDotShape = DotShape(paint)
    currentDotShape?.defineStartCoordinates(x, y)
  }

  override fun onTouchUp () {
    currentDotShape?.let {
      addShapeToEditor(it, shapes)
      currentDotShape = null
    }
  }
}