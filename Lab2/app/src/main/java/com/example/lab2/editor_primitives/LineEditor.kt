package com.example.lab2.editor_primitives

import android.graphics.Paint
import com.example.lab2.Shape
import com.example.lab2.ShapeEditor
import com.example.lab2.shape_primitives.LineShape

class LineEditor (private val paintSettings: Paint, private val shapesList: MutableList<Shape>) : ShapeEditor(paintSettings, shapesList) {
  private var currentLine: LineShape? = null

  override fun onTouchDown(x: Float, y: Float) {
    currentLine = LineShape(paintSettings)
    currentLine?.defineStartCoordinates(x, y)
  }

  override fun onTouchUp() {
    currentLine?.let {
      it.toggleDashed()
      addShapeToEditor(it, shapesList)
    }
    currentLine = null
  }

  override fun handleMouseMovement(x: Float, y: Float) {
    currentLine?.let { lineShape ->
      shapesList.remove(lineShape)
      lineShape.defineEndCoordinates(x, y)
      addShapeToEditor(lineShape, shapesList)
    }
  }
}