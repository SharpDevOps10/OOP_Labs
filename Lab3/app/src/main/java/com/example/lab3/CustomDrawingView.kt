package com.example.lab3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import com.example.lab3.editor_primitive.DotEditor

class CustomDrawingView(context: Context) : View(context) {
  companion object {
    private const val BACKGROUND_COLOUR = Color.WHITE
    private const val DRAWING_COLOR = Color.BLACK
    private const val STROKE_WIDTH = 20f
  }

  private var drawingCanvas = Canvas()
  val shapeList = mutableListOf<Shape>()
  private var currentX = 0f
  private var currentY = 0f

  val drawingSetting = Paint().apply {
    color = DRAWING_COLOR
    strokeWidth = STROKE_WIDTH
    style = Paint.Style.STROKE
    strokeCap = Paint.Cap.ROUND
    strokeJoin = Paint.Join.ROUND
    isAntiAlias = true
  }

  private var actualShape: ShapeEditor = DotEditor(drawingSetting, shapeList)


  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    shapeList.forEach { it.draw(canvas!!) }
  }

  fun setShapePrimitiveEditor(primitiveEditor: ShapeEditor) {
    actualShape = primitiveEditor
  }

  private fun handleTouchUp() {
    invalidate()
    actualShape.onTouchUp()
  }

  private fun handleTouchMove() {
    invalidate()
    actualShape.handleMouseMovement(currentX, currentY)
  }

  private fun handleTouchStart() {
    invalidate()
    actualShape.onTouchDown(currentX, currentY)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    currentX = event!!.x
    currentY = event.y

    when (event.action) {
      MotionEvent.ACTION_MOVE -> handleTouchMove()
      MotionEvent.ACTION_UP -> handleTouchUp()
      MotionEvent.ACTION_DOWN -> handleTouchStart()
    }
    return true
  }

  override fun onSizeChanged(newWidth: Int, newHeight: Int, oldWidth: Int, oldHeight: Int) {
    super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight)
    drawingCanvas = Canvas()
    drawingCanvas.drawColor(BACKGROUND_COLOUR)
  }

}